package com.example.FoodMarket.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

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

    private String phone;

    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    private Boolean isUserSeller;

    private Boolean isUserAdmin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Employee employee;

    /*

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
    private Seller seller;

     */

    public User() { }

    public User(String username, String password, String email, String firstName, String lastName, String address, String phone, LocalDate dateOfBirth, Boolean isUserSeller, Boolean isUserAdmin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.isUserSeller = isUserSeller;
        this.isUserAdmin = isUserAdmin;
    }

    public User(Long id, String username, String password, String email, String firstName, String lastName, String address, String phone, LocalDate dateOfBirth,
                Boolean isUserSeller, Boolean isUserAdmin, Set<Order> orders, Employee employee) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.isUserSeller = isUserSeller;
        this.isUserAdmin = isUserAdmin;
        this.orders = orders;
        this.employee = employee;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
