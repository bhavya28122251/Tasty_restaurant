package org.example.tasty_rest.mapper;

import org.example.tasty_rest.dto.ProductsRequest;
import org.example.tasty_rest.entity.Products;
import org.springframework.stereotype.Service;

@Service
public class ProductsMapper {
    public Products toEntity(ProductsRequest request) {
        return Products.builder()
                .productName(request.productName())
                .price(request.price())
                .build();
    }
}
