package com.example.individualproject.dto.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewProductResponseDTO {
    private Long id;
    private String title;
    private String subTitle;
    private double price;
    private String condition;
}