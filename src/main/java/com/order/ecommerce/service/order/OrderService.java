package com.order.ecommerce.service.order;

import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.entity.*;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderService implements UpdateIOrderService {

    private final IOrderRepository orderRepository;
    private final FindByIdOrderService findByIdOrderService;

    @Autowired
    public OrderService(IOrderRepository orderRepository, FindByIdOrderService findByIdOrderService) {
        this.orderRepository = orderRepository;
        this.findByIdOrderService = findByIdOrderService;
    }
    @Override
    public void updateOrderStatus(String orderId, String status) {
        OrderDto orderDto = findByIdOrderService.findOrderById(orderId);

        if (orderDto == null) {
            log.info("Cannot update status for orderId = {}", orderId);
            return;
        }

        List<OrderStatus> orderStatusList = Arrays.stream(OrderStatus.values()).filter(orderStatus -> orderStatus.toString().equalsIgnoreCase(status)).toList();
        if (orderStatusList.isEmpty()) {
            log.error("Invalid status = {}, failed to update order status for id = {}", status, orderId);
            return;
        }

        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(status.toUpperCase());
        orderRepository.save(order);
        log.info("Successfully updated order status to = {} for order id = {}", status.toUpperCase(), orderId);
    }

}
