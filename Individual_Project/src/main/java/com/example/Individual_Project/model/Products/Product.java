package com.example.Individual_Project.model.Products;

import com.example.Individual_Project.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Product{

    private int productID;
    private String name1;
    private String name2;
    private String serie;
    private int year;
    private String condition;
    private Genre genre;
    private String description;
    private List<Tag> tags;
    private ProductType productType;
    private User user;

    public Product() {}

    public Product(int productID,String name1, String name2, String serie, int year, String condition, Genre genre, String description, List<Tag> tags,ProductType productType, User user)
    {
        this.productID = productID;
        this.name1 = name1;
        this.name2 = name2;
        this.serie = serie;
        this.year = year;
        this.condition = condition;
        this.genre = genre;
        this.description = description;
        this.tags = tags;
        this.productType = productType;
        this.user = user;
    }
}
