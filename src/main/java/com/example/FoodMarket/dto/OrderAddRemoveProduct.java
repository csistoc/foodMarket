package com.example.FoodMarket.dto;

import java.util.Set;

public class OrderAddRemoveProduct {

    private Long id;

    private Set<Long> productIds;

    public OrderAddRemoveProduct() {
    }

    public OrderAddRemoveProduct(Long id, Set<Long> productIds) {
        this.id = id;
        this.productIds = productIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<Long> productIds) {
        this.productIds = productIds;
    }
}
