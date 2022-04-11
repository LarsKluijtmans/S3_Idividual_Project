package com.example.individualproject.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_Images")
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
    @Column(name = "image")
    private String image;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_Id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

}
