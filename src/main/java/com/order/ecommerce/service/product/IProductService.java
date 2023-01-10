package com.order.ecommerce.service.product;

import com.order.ecommerce.dto.ProductDto;

import java.util.List;

public interface IProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto findProductById(String productId);

    ProductDto deleteProductById(String productId);

    List<ProductDto> findAllProducts();

    List<ProductDto> findAllById(List<String> ids);
}
