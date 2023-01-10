package com.order.ecommerce.service.order;

import com.order.ecommerce.dto.*;
import com.order.ecommerce.entity.*;
import com.order.ecommerce.repository.IOrderItemRepository;
import com.order.ecommerce.repository.IOrderRepository;
import com.order.ecommerce.service.product.IProductService;
import com.order.ecommerce.service.utils.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CreateOrderServiceImpl implements CreateIOrderService {

    private final IOrderRepository orderRepository;
    private final IOrderItemRepository orderItemRepository;
    OrderUtils orderUtils;
    private final IProductService productService;

    @Autowired
    public CreateOrderServiceImpl(IOrderRepository orderRepository, IOrderItemRepository orderItemRepository, OrderUtils orderUtils, IProductService productService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderUtils = orderUtils;
        this.productService = productService;
    }

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderDto orderDto) {
        log.info("Creating Order for customer = {}", orderDto.getCustomerId());

        log.info("Verifying all products exists before generating order");
        List<String> productIds = orderDto.getOrderItems().stream().map(orderItemDto -> orderItemDto.getProductId()).distinct().collect(Collectors.toList());
        List<ProductDto> products = productService.findAllById(productIds);
        if (products == null || products.isEmpty() || products.size() != productIds.size()) {
            log.info("Not all product(s) exist, failed to create order!");
            return null;
        }

        Order order = orderUtils.generateOrder(orderDto);
        log.info("Generated order for orderId = {}", order.getOrderId());

        Order savedOrder = orderRepository.save(order);
        String savedOrderId = savedOrder.getOrderId();
        List<OrderItem> orderItemList = buildOrderItems(orderDto.getOrderItems(), savedOrderId);
        orderItemRepository.saveAll(orderItemList);

        log.info("Successfully saved order & order items with id = {} for customer = {} on {}", savedOrder.getOrderId(),  savedOrder.getCustomerId(), savedOrder.getCreatedAt());

        return OrderResponseDto.builder()
                .orderId(savedOrderId)
                .orderStatus(savedOrder.getOrderStatus())
                .build();
    }

    private List<OrderItem> buildOrderItems(List<OrderItemDto> orderItemsDtoList, String orderId) {
        List<OrderItem> orderItemList = orderItemsDtoList
                .stream()
                .map(orderItemDto -> new OrderItem(new OrderItemPk(orderItemDto.getProductId(), orderId), null, null, orderItemDto.getQuantity()))
                .collect(Collectors.toList());
        log.info("Saving order item list for order id = {}", orderId);
        return (List<OrderItem>) orderItemRepository.saveAll(orderItemList);
    }
}
