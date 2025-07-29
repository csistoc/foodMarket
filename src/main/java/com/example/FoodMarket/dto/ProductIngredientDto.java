package com.example.FoodMarket.dto;

public class ProductIngredientDto {

    private Long productId;

    private Long ingredientId;

    public ProductIngredientDto() {
    }

    public ProductIngredientDto(Long productId, Long ingredientId) {
        this.productId = productId;
        this.ingredientId = ingredientId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Long getIngredientId() {
        return ingredientId;
    }
    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
