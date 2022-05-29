package com.example.individualproject.dto.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDTO {

    private String title;
    private String subTitle;
    private String series;
    private int year;
    private double price;
    private String condition;
    private String description;
    private Long genreId;
    private String productType;
    private List<String> images;
    private Long seller;
}
