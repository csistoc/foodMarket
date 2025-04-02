package com.example.FoodMarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPLOYEES")
@PrimaryKeyJoinColumn(name = "ID")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="SELLER_ID", referencedColumnName = "ID", nullable=false)
    private Seller seller;

    public Employee() { }

    public Employee(User user, Seller seller) {
        this.user = user;
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
