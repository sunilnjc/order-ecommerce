package com.order.ecommerce.controller;

import com.order.ecommerce.constants.ExceptionConstants;
import com.order.ecommerce.dto.OrderResponseDto;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.service.order.FindByIdOrderService;
import com.order.ecommerce.service.order.CreateIOrderService;
import com.order.ecommerce.validators.RequestValidation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final CreateIOrderService orderService;
    private final FindByIdOrderService findByIdOrderService;
    RequestValidation requestValidation;

    @Autowired
    public OrderController(CreateIOrderService orderService, FindByIdOrderService findByIdOrderService, RequestValidation requestValidation) {
        this.orderService = orderService;
        this.findByIdOrderService = findByIdOrderService;
        this.requestValidation = requestValidation;
    }

    /**
     * Creates order
     * @param orderDto
     * @return
     */
    @PostMapping
    @Operation(summary = "Create an order", description = "Create an order")
    public OrderResponseDto createOrder(@RequestBody OrderDto orderDto) {
        requestValidation.validateArgument(orderDto);
        return orderService.createOrder(orderDto);
    }

    /**
     * Finds Order by Id
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    @Operation(summary = "Find order", description = "Find order by id")
    public OrderDto findOrderBy(@PathVariable(name = "orderId") String orderId) {
        requestValidation.validateArgument(orderId == null || orderId.isEmpty(), ExceptionConstants.ORDER_ID_NOT_NULL);
        return findByIdOrderService.findOrderById(orderId);
    }

    /**
     * Updates order status
     * @param orderId
     * @param orderStatus
     */
    @PatchMapping("/{orderId}")
    @Operation(summary = "Update order status", description = "Update order status")
    public void updateOrderStatus(@PathVariable("orderId") String orderId,
                                  @RequestParam(name = "orderStatus") String orderStatus) {
        requestValidation.validateArgument(orderId == null || orderId.isEmpty(), ExceptionConstants.ORDER_ID_NOT_NULL);
        requestValidation.validateArgument(orderStatus == null || orderStatus.isEmpty(), ExceptionConstants.ORDER_STATUS_NOT_NULL);
        orderService.updateOrderStatus(orderId, orderStatus);
    }

}
