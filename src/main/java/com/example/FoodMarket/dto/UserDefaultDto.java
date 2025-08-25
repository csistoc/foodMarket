package com.example.FoodMarket.dto;

import java.time.LocalDate;
import java.util.Set;

public class UserDefaultDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    private LocalDate dateOfBirth;

    private Set<Long> orderIds;

    private Set<Long> sellerIds;

    public UserDefaultDto() {}

    public UserDefaultDto(Long id, String username, String password, String email, String firstName, String lastName, String address, String phone, LocalDate dateOfBirth, Set<Long> orders, Set<Long> sellers) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.orderIds = orders;
        this.sellerIds = sellers;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(Set<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public Set<Long> getSellerIds() {
        return sellerIds;
    }

    public void setSellerIds(Set<Long> sellerIds) {
        this.sellerIds = sellerIds;
    }
}
