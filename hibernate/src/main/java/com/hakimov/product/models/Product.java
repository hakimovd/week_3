package com.hakimov.product.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 100, message = "Name should be between 1 and 100 char")
    @Column(name = "name")
    private String name;

    @Min(value = 1, message = "Price should be greater than 1")
    @Column(name = "price")
    private int price;

    @Min(value = 1, message = "Calories should be greater than 1")
    @Column(name = "calories")
    private int calories;

    @NotEmpty(message = "Composition should not be empty")
    @Size(min = 1, max = 200, message = "Name should be between 1 and 100 char")
    @Column(name = "composition")
    private String composition;
}
