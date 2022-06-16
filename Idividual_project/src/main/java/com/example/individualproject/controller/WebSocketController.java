package com.example.individualproject.controller;

import com.example.individualproject.business.ProductService;
import com.example.individualproject.dto.products.GetProductDTO;
import com.example.individualproject.dto.products.NewProductDTO;
import com.example.individualproject.dto.products.NewProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final ProductService productService;

    @MessageMapping("/NewApp")
    @SendTo("/topic/newApps")
    public NewProductResponseDTO newApplication(@Payload final NewProductDTO newProductDTO) {

        List<GetProductDTO> products = productService.getProducts(newProductDTO.getTitle());

        GetProductDTO latest = products.get(products.size() -1);

        return NewProductResponseDTO.builder()
                .id(latest.getId())
                .title(latest.getTitle())
                .subTitle(latest.getSubTitle())
                .condition(latest.getCondition())
                .price(latest.getPrice())
                .build();
    }
}
