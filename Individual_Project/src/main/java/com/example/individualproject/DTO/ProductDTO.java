package com.example.individualproject.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String title;
    private String sub_title;
    private String series;
    private int year;
    private double price;
    private String condition_;
    private String description;
    private String genre;
    private boolean sold;
    private String product_type;
}
