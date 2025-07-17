package com.example.FoodMarket.dto;

public class UserAddressDto {

    private Long id;

    private String address;

    public UserAddressDto() {
    }

    public UserAddressDto(Long id, String address) {
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
