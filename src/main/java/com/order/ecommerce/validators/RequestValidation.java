package com.order.ecommerce.validators;

import com.order.ecommerce.constants.ExceptionConstants;
import com.order.ecommerce.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Component
public class RequestValidation {

    public void validateArgument(OrderDto orderDto) {
        validateArgument(orderDto.getCustomerId() == null || orderDto.getCustomerId().isEmpty(), ExceptionConstants.CUSTOMER_ID_NOT_NULL);
        validateArgument(orderDto.getTitle() == null || orderDto.getTitle().isEmpty(), ExceptionConstants.TITLE_NOT_NULL);
        validateArgument(orderDto.getPaymentMode() == null || orderDto.getPaymentMode().isEmpty(), ExceptionConstants.PAYMENT_MODE_NOT_NULL);
        validateArgument(orderDto.getBillingAddress() == null, ExceptionConstants.BILLING_ADDRESS_NOT_NULL);
        validateArgument(orderDto.getOrderItems() == null || orderDto.getOrderItems().isEmpty(), ExceptionConstants.ORDER_ITEMS_NOT_NULL);
        validateArgument(orderDto.getOrderStatus() == null || orderDto.getOrderStatus().isEmpty(), ExceptionConstants.ORDER_STATUS_NOT_NULL);
    }

    public void validateArgument(boolean condition, String message) {
        if (condition) {
            log.error("Error while processing request with message = {}", message);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }
}
