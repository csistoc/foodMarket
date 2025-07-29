package com.example.FoodMarket.dto;

public class ProductCategoryDto {

    private Long productId;

    private Long categoryId;

    public ProductCategoryDto() { }

    public ProductCategoryDto(Long productId, Long categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
