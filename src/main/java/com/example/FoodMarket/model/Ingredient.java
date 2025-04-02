package com.example.FoodMarket.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "INGREDIENTS")
@PrimaryKeyJoinColumn(name = "ID")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "INGREDIENTS")
    private Set<Product> productIngredients;

    public Ingredient() { }

    public Ingredient(String name, Set<Product> productIngredients) {
        this.name = name;
        this.productIngredients = productIngredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProductIngredients() {
        return productIngredients;
    }

    public void setProductIngredients(Set<Product> productIngredients) {
        this.productIngredients = productIngredients;
    }
}
