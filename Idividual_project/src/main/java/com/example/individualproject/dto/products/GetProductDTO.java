package com.example.individualproject.dto.products;

import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.Product;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Data
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

    public GetProductDTO(Product p)
    {
        this.id = p.getId();
        this.title = p.getTitle();
        this.subTitle = p.getSubTitle();
        this.series = p.getSeries();
        this.year = p.getYear();
        this.price = p.getPrice();
        this.condition = p.getCondition();
        this.description = p.getDescription();
        this.genre = p.getGenre();
        this.productType = p.getProductType();

        this.images = new ArrayList<>();
        for (Image image: p.getImages()) {
            images.add(image.getImageUrl());
        }
    }
}
