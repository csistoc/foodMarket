package com.example.FoodMarket.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "order_statuses")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    public OrderStatus() { }

    public OrderStatus(String name, Set<Order> orders) {
        this.name = name;
        this.orders = orders;
    }

    public OrderStatus(String name) {
        this.name = name;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
