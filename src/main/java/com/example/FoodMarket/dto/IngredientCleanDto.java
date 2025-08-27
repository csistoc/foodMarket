package com.example.FoodMarket.dto;

import java.util.Set;

public class IngredientCleanDto {

    private String name;

    private Set<Long> productIds;

    public IngredientCleanDto() {
    }

    public IngredientCleanDto(String name, Set<Long> productIds) {

        this.name = name;
        this.productIds = productIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<Long> productIds) {
        this.productIds = productIds;
    }
}
