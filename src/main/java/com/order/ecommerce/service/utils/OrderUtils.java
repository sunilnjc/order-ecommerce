package com.order.ecommerce.service.utils;

import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.entity.Address;
import com.order.ecommerce.entity.Order;
import com.order.ecommerce.entity.Payment;
import com.order.ecommerce.enums.OrderStatus;
import com.order.ecommerce.mapper.OrderDetailsMapper;
import com.order.ecommerce.service.address.AddressService;
import com.order.ecommerce.service.payments.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderUtils {

    private final OrderDetailsMapper orderDetailsMapper = Mappers.getMapper(OrderDetailsMapper.class);
     PaymentService paymentService;

     AddressService addressService;

     @Autowired
    public OrderUtils(PaymentService paymentService, AddressService addressService) {
        this.paymentService = paymentService;
        this.addressService = addressService;
    }

    public Order generateOrder(OrderDto orderDto) {
        Order order = orderDetailsMapper.toOrderEntity(orderDto);
        order.setOrderId(UUID.randomUUID().toString());
        order.setCreatedAt(LocalDate.now());

        order.setOrderStatus(OrderStatus.PROCESSING.name());

        Payment payment = paymentService.buildAndSavePayment(orderDto.getAmount(), orderDto.getPaymentMode());
        order.setPayment(payment);

        Address billingAddress = addressService.buildAndLoadAddress(orderDto.getBillingAddress());
        Address shippingAddress = addressService.buildAndLoadAddress(orderDto.getShippingAddress());
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(shippingAddress);
        return order;
    }

}
