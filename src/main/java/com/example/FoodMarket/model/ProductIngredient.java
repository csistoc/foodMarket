package com.example.FoodMarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUCT_INGREDIENTS")
@PrimaryKeyJoinColumn(name = "ID")
public class ProductIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
