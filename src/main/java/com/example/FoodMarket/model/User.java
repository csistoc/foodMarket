package com.example.FoodMarket.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private Date dateOfBirth;

    private Boolean isUserSeller;

    private Boolean isUserAdmin;

    public User() { }

    public User(String username, String password, String email, String firstName, String lastName, String address,
                Date dateOfBirth, Boolean isUserSeller, Boolean isUserAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.isUserSeller = isUserSeller;
        this.isUserAdmin = isUserAdmin;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getUserSeller() {
        return isUserSeller;
    }

    public void setUserSeller(Boolean userSeller) {
        isUserSeller = userSeller;
    }

    public Boolean getUserAdmin() {
        return isUserAdmin;
    }

    public void setUserAdmin(Boolean userAdmin) {
        isUserAdmin = userAdmin;
    }
}
