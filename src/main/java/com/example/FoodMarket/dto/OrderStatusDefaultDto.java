package com.example.FoodMarket.dto;

import java.util.Set;

public class OrderStatusDefaultDto {

    private Long id;

    private String name;

    private Set<Long> orderIds;

    public OrderStatusDefaultDto() {
    }

    public OrderStatusDefaultDto(Long id, String name, Set<Long> orderIds) {
        this.id = id;
        this.name = name;
        this.orderIds = orderIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
