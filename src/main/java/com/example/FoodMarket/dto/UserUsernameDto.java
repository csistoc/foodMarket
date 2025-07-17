package com.example.FoodMarket.dto;

public class UserUsernameDto {

    private Long id;

    private String username;

    public UserUsernameDto() {
    }

    public UserUsernameDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
