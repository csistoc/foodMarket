package com.example.FoodMarket.dto;

public class UserPhoneDto {

    private Long id;

    private String phone;

    public UserPhoneDto() {
    }

    public UserPhoneDto(Long id, String phone) {
        this.id = id;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
