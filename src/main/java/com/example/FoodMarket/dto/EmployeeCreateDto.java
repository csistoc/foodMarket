package com.example.FoodMarket.dto;

import com.example.FoodMarket.model.Seller;
import com.example.FoodMarket.model.User;

public class EmployeeCreateDto {

    private User user;

    private Seller seller;

    public EmployeeCreateDto() {
    }

    public EmployeeCreateDto(User user, Seller seller) {
        this.user = user;
        this.seller = seller;
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
