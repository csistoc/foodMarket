package com.example.FoodMarket.dto;

import java.util.Set;

public class ProductCleanDto {

    private String name;

    private Set<Long> categoryIds;

    private Set<Long> ingredientIds;

    private Set<Long> sellerIds;

    private Set<Long> orderIds;

    public ProductCleanDto() {
    }

    public ProductCleanDto(String name, Set<Long> categoryIds, Set<Long> ingredientIds, Set<Long> sellerIds, Set<Long> orderIds) {
        this.name = name;
        this.categoryIds = categoryIds;
        this.ingredientIds = ingredientIds;
        this.sellerIds = sellerIds;
        this.orderIds = orderIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Set<Long> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(Set<Long> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }

    public Set<Long> getSellerIds() {
        return sellerIds;
    }

    public void setSellerIds(Set<Long> sellerIds) {
        this.sellerIds = sellerIds;
    }

    public Set<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(Set<Long> orderIds) {
        this.orderIds = orderIds;
    }
}
