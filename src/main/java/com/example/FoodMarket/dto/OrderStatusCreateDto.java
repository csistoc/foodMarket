package com.example.FoodMarket.dto;

import java.util.Set;

public class OrderStatusCreateDto {

    private String name;

    private Set<Long> orderIds;

    public OrderStatusCreateDto() {
    }

    public OrderStatusCreateDto(String name, Set<Long> orderIds) {
        this.name = name;
        this.orderIds = orderIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(Set<Long> orderIds) {
        this.orderIds = orderIds;
    }
}
