package com.example.individualproject.dto.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewProductDTO {
    private String title;
    private String subTitle;
    private String price;
    private String condition;
}
