package com.example.FoodMarket.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "PRODUCTS")
@PrimaryKeyJoinColumn(name = "ID")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "PRODUCT_CATEGORIES",
            joinColumns = { @JoinColumn(name = "PRODUCT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID") }
    )
    private Set<Category> productCategories;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "PRODUCT_INGREDIENTS",
            joinColumns = { @JoinColumn(name = "PRODUCT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "INGREDIENT_ID") }
    )
    private Set<Ingredient> productIngredients;

    @ManyToMany(mappedBy = "PRODUCTS")
    private Set<Seller> sellerProducts;

    @ManyToMany(mappedBy = "PRODUCTS")
    private Set<Order> orderProducts;

    public Product() { }

    public Product(String name, Set<Category> productCategories, Set<Ingredient> productIngredients, Set<Seller> sellerProducts, Set<Order> orderProducts) {
        this.name = name;
        this.productCategories = productCategories;
        this.productIngredients = productIngredients;
        this.sellerProducts = sellerProducts;
        this.orderProducts = orderProducts;
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

    public Set<Category> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<Category> productCategories) {
        this.productCategories = productCategories;
    }

    public Set<Ingredient> getProductIngredients() {
        return productIngredients;
    }

    public void setProductIngredients(Set<Ingredient> productIngredients) {
        this.productIngredients = productIngredients;
    }

    public Set<Seller> getSellerProducts() {
        return sellerProducts;
    }

    public void setSellerProducts(Set<Seller> sellerProducts) {
        this.sellerProducts = sellerProducts;
    }

    public Set<Order> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<Order> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
