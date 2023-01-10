package com.order.ecommerce.service.order;

import com.order.ecommerce.dto.*;
import com.order.ecommerce.entity.*;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.enums.PaymentStatus;
import com.order.ecommerce.mapper.OrderDetailsMapper;
import com.order.ecommerce.repository.IAddressRepository;
import com.order.ecommerce.repository.IOrderItemRepository;
import com.order.ecommerce.repository.IOrderRepository;
import com.order.ecommerce.repository.IPaymentRepository;
import com.order.ecommerce.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindOrderByIdServiceImpl implements FindByIdOrderService {

    private final IOrderRepository orderRepository;
    private final OrderDetailsMapper orderDetailsMapper = Mappers.getMapper(OrderDetailsMapper.class);

    @Override
    public OrderDto findOrderById(String orderId) {
        log.info("finding order for orderId = {}", orderId);
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            log.info("Cannot find order with id = {}", orderId);
            return null;
        }

        log.info("Successfully found order for orderId = {}", orderId);
        return orderDetailsMapper.toOrderDto(order.get());
    }

}
