package com.example.individualproject.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "images")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 1 ,max = 200)
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="product_Id", nullable=false)
    private Product product;

}
