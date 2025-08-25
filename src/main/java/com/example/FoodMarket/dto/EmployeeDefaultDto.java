package com.example.FoodMarket.dto;

import com.example.FoodMarket.model.Seller;
import com.example.FoodMarket.model.User;

public class EmployeeDefaultDto {

    private Long id;

    private User user;

    private Seller seller;

    public EmployeeDefaultDto() {
    }

    public EmployeeDefaultDto(Long id, User user, Seller seller) {
        this.id = id;
        this.user = user;
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
