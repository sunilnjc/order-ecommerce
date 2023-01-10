package com.order.ecommerce.mapper;

import com.order.ecommerce.dto.AddressDto;
import com.order.ecommerce.dto.AddressDto.AddressDtoBuilder;
import com.order.ecommerce.dto.OrderDto;
import com.order.ecommerce.dto.OrderDto.OrderDtoBuilder;
import com.order.ecommerce.dto.OrderItemDto;
import com.order.ecommerce.dto.OrderItemDto.OrderItemDtoBuilder;
import com.order.ecommerce.entity.Address;
import com.order.ecommerce.entity.Order;
import com.order.ecommerce.entity.OrderItem;
import com.order.ecommerce.entity.Payment;
import com.order.ecommerce.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-09T09:35:12+0400",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 16.0.1 (Oracle Corporation)"
)
public class OrderDetailsMapperImpl implements OrderDetailsMapper {

    @Override
    public OrderDto toOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDtoBuilder orderDto = OrderDto.builder();

        orderDto.amount( orderPaymentAmount( order ) );
        orderDto.paymentMode( orderPaymentPaymentMode( order ) );
        orderDto.customerId( order.getCustomerId() );
        orderDto.subTotal( order.getSubTotal() );
        orderDto.totalAmt( order.getTotalAmt() );
        orderDto.tax( order.getTax() );
        orderDto.shippingCharges( order.getShippingCharges() );
        orderDto.title( order.getTitle() );
        orderDto.shippingMode( order.getShippingMode() );
        orderDto.billingAddress( toAddressDto( order.getBillingAddress() ) );
        orderDto.shippingAddress( toAddressDto( order.getShippingAddress() ) );
        orderDto.orderItems( orderItemListToOrderItemDtoList( order.getOrderItems() ) );
        orderDto.orderStatus( order.getOrderStatus() );

        return orderDto.build();
    }

    @Override
    public Order toOrderEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderStatus( orderDto.getOrderStatus() );
        order.setCustomerId( orderDto.getCustomerId() );
        order.setSubTotal( orderDto.getSubTotal() );
        order.setTotalAmt( orderDto.getTotalAmt() );
        order.setTax( orderDto.getTax() );
        order.setShippingCharges( orderDto.getShippingCharges() );
        order.setTitle( orderDto.getTitle() );
        order.setShippingMode( orderDto.getShippingMode() );
        order.setBillingAddress( toAddressEntity( orderDto.getBillingAddress() ) );
        order.setShippingAddress( toAddressEntity( orderDto.getShippingAddress() ) );
        order.setOrderItems( orderItemDtoListToOrderItemList( orderDto.getOrderItems() ) );

        return order;
    }

    @Override
    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDtoBuilder orderItemDto = OrderItemDto.builder();

        orderItemDto.productId( orderItemProductProductId( orderItem ) );
        orderItemDto.quantity( orderItem.getQuantity() );

        return orderItemDto.build();
    }

    @Override
    public OrderItem toOrderItemEntity(OrderItemDto orderItemDto) {
        if ( orderItemDto == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity( orderItemDto.getQuantity() );

        return orderItem;
    }

    @Override
    public Address toAddressEntity(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setAddress1( addressDto.getAddress1() );
        address.setAddress2( addressDto.getAddress2() );
        address.setCity( addressDto.getCity() );
        address.setState( addressDto.getState() );
        address.setZip( addressDto.getZip() );
        address.setEmail( addressDto.getEmail() );
        address.setPhone( addressDto.getPhone() );

        return address;
    }

    @Override
    public AddressDto toAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDtoBuilder addressDto = AddressDto.builder();

        addressDto.address1( address.getAddress1() );
        addressDto.address2( address.getAddress2() );
        addressDto.city( address.getCity() );
        addressDto.state( address.getState() );
        addressDto.zip( address.getZip() );
        addressDto.email( address.getEmail() );
        addressDto.phone( address.getPhone() );

        return addressDto.build();
    }

    private double orderPaymentAmount(Order order) {
        if ( order == null ) {
            return 0.0d;
        }
        Payment payment = order.getPayment();
        if ( payment == null ) {
            return 0.0d;
        }
        double amount = payment.getAmount();
        return amount;
    }

    private String orderPaymentPaymentMode(Order order) {
        if ( order == null ) {
            return null;
        }
        Payment payment = order.getPayment();
        if ( payment == null ) {
            return null;
        }
        String paymentMode = payment.getPaymentMode();
        if ( paymentMode == null ) {
            return null;
        }
        return paymentMode;
    }

    protected List<OrderItemDto> orderItemListToOrderItemDtoList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDto> list1 = new ArrayList<OrderItemDto>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( toOrderItemDto( orderItem ) );
        }

        return list1;
    }

    protected List<OrderItem> orderItemDtoListToOrderItemList(List<OrderItemDto> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemDto orderItemDto : list ) {
            list1.add( toOrderItemEntity( orderItemDto ) );
        }

        return list1;
    }

    private String orderItemProductProductId(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }
        Product product = orderItem.getProduct();
        if ( product == null ) {
            return null;
        }
        String productId = product.getProductId();
        if ( productId == null ) {
            return null;
        }
        return productId;
    }
}
