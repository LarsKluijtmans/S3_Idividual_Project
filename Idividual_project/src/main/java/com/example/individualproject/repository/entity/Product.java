package com.example.individualproject.repository.entity;

import com.example.individualproject.dto.products.UpdateProductRequestDTO;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "product")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product{

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 1 ,max = 50)
    @Column(name = "title")
    private String title;

    @Length(min = 1 ,max = 50)
    @Column(name = "sub_title")
    private String subTitle;

    @Length(min = 1 ,max = 30)
    @Column(name = "series")
    private String series;

    @Column(name = "year")
    private int year;

    @Column(name = "price")
    private double price;

    @Length(min = 1 ,max = 20)
    @Column(name = "condition_")
    private String condition;

    @Length(min = 1 ,max = 500)
    @Column(name = "description")
    private String description;

    @Length(min = 1 ,max = 30)
    @Column(name = "genre")
    private String genre;

    @Column(name = "sold")
    private boolean sold;

    @Length(min = 1 ,max = 30)
    @Column(name = "product_type")
    private String productType;

    @OneToMany(mappedBy="product")
    private List<Image> images;

    public Product(UpdateProductRequestDTO productRequestDTO) {
        this.setId(productRequestDTO.getProductId());
        this.setTitle(productRequestDTO.getProductInfo().getTitle());
        this.setSubTitle(productRequestDTO.getProductInfo().getSubTitle());
        this.setSeries(productRequestDTO.getProductInfo().getSeries());
        this.setYear(productRequestDTO.getProductInfo().getYear());
        this.setPrice(productRequestDTO.getProductInfo().getPrice());
        this.setCondition(productRequestDTO.getProductInfo().getCondition());
        this.setDescription(productRequestDTO.getProductInfo().getDescription());
        this.setGenre(productRequestDTO.getProductInfo().getGenre());
        this.setSold(false);
        this.setProductType(productRequestDTO.getProductInfo().getProductType());
    }
}
