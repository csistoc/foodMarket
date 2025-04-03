package com.example.FoodMarket.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "SELLERS")
@PrimaryKeyJoinColumn(name = "id")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    @OneToMany(mappedBy = "SELLERS")
    private Set<Employee> employees;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "SELLER_PRODUCTS",
            joinColumns = { @JoinColumn(name = "SELLER_ID") },
            inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID") }
    )
    private Set<Product> sellerProducts;

    public Seller() { }

    public Seller(String name, String address, String phone, Set<Employee> employees, Set<Product> sellerProducts) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.employees = employees;
        this.sellerProducts = sellerProducts;
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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Product> getSellerProducts() {
        return sellerProducts;
    }

    public void setSellerProducts(Set<Product> sellerProducts) {
        this.sellerProducts = sellerProducts;
    }
}
