package com.order.ecommerce.service.order;

import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.dto.OrderResponseDto;

public interface FindByIdOrderService {

     OrderDto findOrderById(String id);

}
