package com.example.Individual_Project.model.Users;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class Address {

    private String address;
    private String country;
    private String city;
    private String zip;
    private String postalCode;

    public Address(){}

    public Address(String address, String country,String city,String zip, String postalCode)
    {
        this.address = address;
        this.country = country;
        this.city = city;
        this.zip = zip;
        this.postalCode = postalCode;
    }

}
