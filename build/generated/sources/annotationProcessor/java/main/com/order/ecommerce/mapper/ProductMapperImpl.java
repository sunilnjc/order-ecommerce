package com.order.ecommerce.mapper;

import com.order.ecommerce.dto.ProductDto;
import com.order.ecommerce.dto.ProductDto.ProductDtoBuilder;
import com.order.ecommerce.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-09T09:35:13+0400",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 16.0.1 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProductEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( productDto.getProductId() );
        product.setSku( productDto.getSku() );
        product.setTitle( productDto.getTitle() );
        product.setDescription( productDto.getDescription() );
        product.setPrice( productDto.getPrice() );

        return product;
    }

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDtoBuilder productDto = ProductDto.builder();

        productDto.productId( product.getProductId() );
        productDto.sku( product.getSku() );
        productDto.title( product.getTitle() );
        productDto.description( product.getDescription() );
        productDto.price( product.getPrice() );

        return productDto.build();
    }

    @Override
    public List<ProductDto> map(List<Product> product) {
        if ( product == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( product.size() );
        for ( Product product1 : product ) {
            list.add( toProductDto( product1 ) );
        }

        return list;
    }
}
