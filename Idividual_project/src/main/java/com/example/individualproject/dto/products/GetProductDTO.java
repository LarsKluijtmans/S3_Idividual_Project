package com.example.individualproject.dto.products;

import com.example.individualproject.dto.users.GetUserDTO;
import com.example.individualproject.repository.entity.Image;
import com.example.individualproject.repository.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class GetProductDTO {

    private Long id;
    private BasicProductInfo productInfo;
    private GetUserDTO seller;

    public GetProductDTO(Product p)
    {
        this.productInfo = new BasicProductInfo();

        this.id = p.getId();
        this.productInfo.setTitle(p.getTitle());
        this.productInfo.setSubTitle(p.getSubTitle());
        this.productInfo.setSeries(p.getSeries());
        this.productInfo.setYear(p.getYear());
        this.productInfo.setPrice(p.getPrice());
        this.productInfo.setCondition(p.getCondition());
        this.productInfo.setDescription(p.getDescription());
        this.productInfo.setGenreId(p.getGenre().getId());
        this.productInfo.setProductType(p.getProductType());

        this.seller = new GetUserDTO( p.getSeller());
        this.productInfo.setImages(new ArrayList<>());
        for (Image image: p.getImages()) {
            productInfo.getImages().add(image.getImageUrl());
        }
    }
}
