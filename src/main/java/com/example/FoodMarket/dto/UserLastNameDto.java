package com.example.FoodMarket.dto;

public class UserLastNameDto {

    private Long id;

    private String lastName;

    public UserLastNameDto() {
    }

    public UserLastNameDto(Long id, String lastName) {
        this.id = id;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
