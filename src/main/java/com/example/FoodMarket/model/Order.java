package com.example.FoodMarket.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ORDERS")
@PrimaryKeyJoinColumn(name = "ID")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "ID", nullable = false)
    private Seller seller;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_STATUS_ID", referencedColumnName = "ID", nullable = false)
    private OrderStatus orderStatus;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "ORDER_PRODUCTS",
            joinColumns = { @JoinColumn(name = "PRODUCT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ORDER_ID") }
    )
    private Set<Product> orderProducts;

    public Order() { }

    public Order(Seller seller, User user, OrderStatus orderStatus, Set<Product> orderProducts) {
        this.seller = seller;
        this.user = user;
        this.orderStatus = orderStatus;
        this.orderProducts = orderProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
