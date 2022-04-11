package com.example.individualproject.business.impl;

import com.example.individualproject.DTO.ProductDTO;
import com.example.individualproject.repository.entity.Product;

final class ProductDTOConverter {
    private ProductDTOConverter() {
    }

    public static ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .sub_title(product.getSub_title())
                .series(product.getSeries())
                .year(product.getYear())
                .price(product.getPrice())
                .condition_(product.getCondition())
                .description(product.getDescription())
                .genre(product.getGenre())
                .sold(false)
                .product_type(product.getProduct_type())
                .build();
    }
}
