package com.order.ecommerce.service.order;

import com.order.ecommerce.dto.OrderResponseDto;
import com.order.ecommerce.dto.OrderDto;

public interface CreateIOrderService {
     OrderResponseDto createOrder(OrderDto orderDto);
    default void updateOrderStatus(String id, String status){
    }


}
