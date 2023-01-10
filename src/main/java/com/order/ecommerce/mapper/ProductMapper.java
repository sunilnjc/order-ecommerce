package com.order.ecommerce.mapper;

import com.order.ecommerce.dto.ProductDto;
import com.order.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    Product toProductEntity(ProductDto productDto);

    ProductDto toProductDto(Product product);

    List<ProductDto> map(List<Product> product);
}
