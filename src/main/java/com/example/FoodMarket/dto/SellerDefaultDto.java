package com.example.FoodMarket.dto;

import java.util.Set;

public class SellerDefaultDto {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private Set<Long> productIds;

    private Set<Long> userIds;

    public SellerDefaultDto() {
    }

    public SellerDefaultDto(Long id, String name, String address, String phone, Set<Long> productIds, Set<Long> userIds) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.productIds = productIds;
        this.userIds = userIds;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<Long> productIds) {
        this.productIds = productIds;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }
}
