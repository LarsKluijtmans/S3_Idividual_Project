package com.example.individualproject.dto.products;

import com.example.individualproject.dto.users.GetUserDTO;
import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GetProductDTO {

    private Long id;
    private String title;
    private String subTitle;
    private String series;
    private int year;
    private double price;
    private String condition;
    private String description;
    private String genre;
    private String productType;
    private List<String> images;
    private GetUserDTO seller;

    public GetProductDTO(Product p) {
        this.id = p.getId();
        this.setTitle(p.getTitle());
        this.setSubTitle(p.getSubTitle());
        this.setSeries(p.getSeries());
        this.setYear(p.getYear());
        this.setPrice(p.getPrice());
        this.setCondition(p.getCondition());
        this.setDescription(p.getDescription());
        this.setGenre(p.getGenre().getGenre());
        this.setProductType(p.getProductType());

        this.seller = GetUserDTO.builder()
                .username(p.getSeller().getUsername())
                .firstName(p.getSeller().getFirstname())
                .lastName(p.getSeller().getLastname())
                .email(p.getSeller().getEmail())
                .phoneNumber(p.getSeller().getPhonenumber())
                .position("NORMAL")
                .build();

        this.setImages(new ArrayList<>());
        for (Image image : p.getImages()) {
            this.getImages().add(image.getImageUrl());
        }
    }
}
