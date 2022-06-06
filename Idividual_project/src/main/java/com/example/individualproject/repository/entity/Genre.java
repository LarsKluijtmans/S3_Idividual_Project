package com.example.individualproject.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    //Todo intellij is telling me @Data can cause severe performance and memory issues.

    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "id")
    private Long id;

    @Column(name = "genre")
    private String genre;

    @OneToMany(mappedBy = "genre")
    private List<Product> products;

}

