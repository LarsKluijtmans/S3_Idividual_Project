package com.example.Individual_Project.model.Products;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Tag {

    private int id;
    private String name;

    public Tag(){}
    public Tag(int id, String name){
        this.name = name;
        this.id = id;
    }
}
