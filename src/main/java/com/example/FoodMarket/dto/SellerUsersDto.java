package com.example.FoodMarket.dto;

public class SellerUsersDto {

    private Long sellerId;

    private Long userId;

    public SellerUsersDto() {
    }

    public SellerUsersDto(Long sellerId, Long userId) {
        this.sellerId = sellerId;
        this.userId = userId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
