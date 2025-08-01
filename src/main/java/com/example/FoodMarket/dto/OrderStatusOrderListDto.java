package com.example.FoodMarket.dto;

import java.util.Set;

public class OrderStatusOrderListDto {

    private Long id;

    private Set<Long> orderIds;

    public OrderStatusOrderListDto() {
    }

    public OrderStatusOrderListDto(Long id, Set<Long> orderIds) {
        this.id = id;
        this.orderIds = orderIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(Set<Long> orderIds) {
        this.orderIds = orderIds;
    }
}
