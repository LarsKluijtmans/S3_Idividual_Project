package com.example.individualproject.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends User{
}
