package com.example.Individual_Project.repository.Test;

import com.example.Individual_Project.model.NormalUser;
import com.example.Individual_Project.model.Products.Genre;
import com.example.Individual_Project.model.Products.Product;
import com.example.Individual_Project.model.Products.ProductType;
import com.example.Individual_Project.model.Products.Tag;
import com.example.Individual_Project.model.User;

import com.example.Individual_Project.model.Users.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Repository
@Qualifier("Local")
public class DataBase {

    public DataBase()
    {
        users = new ArrayList<User>();
        products = new ArrayList<Product>();

        //Users
        Account lars1account = new Account("Lars", "Lars");
        User lars1 = new User(1,"Lars", "Kluijtmans", "lars.kluijtmans@gmail.com",1234567, lars1account);

        Account lars2account = new Account("Lars2", "Lars2");
        User lars2 = new User(2,"Lars2", "Kluijtmans2", "lars.kluijtmans@gmail.com2",2345679, lars2account);

        users.add(lars1);
        users.add(lars2);

        //Tags
        List<Tag> tags = new ArrayList<Tag>();
        Tag tag1 = new Tag(1,"NormalGame");
        Tag tag2 = new Tag(2,"VeryOldGame");
        Tag tag3 = new Tag(3,"Most popular");

        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        //Products
        Product product1 = new Product(1,"1", "Pokemon 2001", "Pokemon", 2001, "Great", Genre.JRPG,"Pokemon diamond a clasic game ..", tags, ProductType.Game, lars1);
        products.add(product1);
    }

    public List<User> users ;
    public List<Product> products;
}
