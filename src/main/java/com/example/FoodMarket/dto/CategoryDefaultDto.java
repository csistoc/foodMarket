package com.example.FoodMarket.dto;

import java.util.Set;

public class CategoryDefaultDto {

    private Long id;

    private String name;

    private Set<Long> productIds;

    public CategoryDefaultDto() {
    }

    public CategoryDefaultDto(Long id, String name, Set<Long> productIds) {
        this.id = id;
        this.name = name;
        this.productIds = productIds;
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

    public Set<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<Long> productIds) {
        this.productIds = productIds;
    }
}
