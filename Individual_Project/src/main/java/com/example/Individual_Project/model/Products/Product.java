package com.example.Individual_Project.model.Products;

import com.example.Individual_Project.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product{

    private int productID;
    private String name1;
    private String name2;
    private String serie;
    private int year;
    private double price;
    private String condition;
    private Genre genre;
    private String description;
    private List<Tag> tags;
    private ProductType productType;
    private User user;
}
