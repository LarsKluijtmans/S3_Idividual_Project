package com.example.individualproject.dto.products;

import com.example.individualproject.repository.entity.NormalUser;
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
    private BasicProductInfo productInfo;
    private NormalUser seller;
}
