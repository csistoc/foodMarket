package com.example.FoodMarket.dto;

public class SellerProductDto {

    private Long sellerId;
    private Long productId;

    public SellerProductDto() {
    }

    public SellerProductDto(Long sellerId, Long productId) {
        this.sellerId = sellerId;
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
