package com.example.individualproject.DTO.Products;

import com.example.individualproject.repository.entity.Product;

import lombok.Data;

import java.util.List;

@Data
public class GetProductDTO {

    private String title;
    private String sub_title;
    private String series;
    private int year;
    private double price;
    private String condition_;
    private String description;
    private String genre;
    private String product_type;


    public GetProductDTO(Product p)
    {
        this.title = p.getTitle();
        this.sub_title = p.getSubTitle();
        this.series = p.getSeries();
        this.year = p.getYear();
        this.price = p.getPrice();
        this.condition_ = p.getCondition();
        this.description = p.getDescription();
        this.genre = p.getGenre();
        this.product_type = p.getProduct_type();
    }
}
