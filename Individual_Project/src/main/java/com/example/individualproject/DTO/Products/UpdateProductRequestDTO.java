package com.example.individualproject.DTO.Products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDTO {

    private Long productId;
    private String title;
    private String sub_title;
    private String series;
    private int year;
    private double price;
    private String condition_;
    private String description;
    private String genre;
    private String product_type;
}
