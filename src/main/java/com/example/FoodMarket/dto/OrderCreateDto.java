package com.example.FoodMarket.dto;

import com.example.FoodMarket.model.OrderStatus;
import com.example.FoodMarket.model.Seller;
import com.example.FoodMarket.model.User;

import java.util.Set;

public class OrderCreateDto {

    private Seller seller;

    private User user;

    private OrderStatus orderStatus;

    private Set<Long> productIds;

    public OrderCreateDto() {
    }

    public OrderCreateDto(Seller seller, User user, OrderStatus orderStatus, Set<Long> productIds) {
        this.seller = seller;
        this.user = user;
        this.orderStatus = orderStatus;
        this.productIds = productIds;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<Long> productIds) {
        this.productIds = productIds;
    }
}
