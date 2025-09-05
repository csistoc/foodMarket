package com.example.FoodMarket.dto;

import com.example.FoodMarket.model.OrderStatus;
import com.example.FoodMarket.model.Seller;
import com.example.FoodMarket.model.User;

import java.util.Set;

public class OrderDefaultDto {

    private Long id;

    private Seller seller;

    private User user;

    private OrderStatus orderStatus;

    private Set<Long> productIds;

    public OrderDefaultDto() {
    }

    public OrderDefaultDto(Long id, Seller seller, User user, OrderStatus orderStatus, Set<Long> productIds) {
        this.id = id;
        this.seller = seller;
        this.user = user;
        this.orderStatus = orderStatus;
        this.productIds = productIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
