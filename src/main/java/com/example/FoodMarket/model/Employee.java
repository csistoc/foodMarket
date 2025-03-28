package com.example.FoodMarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPLOYEES")
@PrimaryKeyJoinColumn(name = "id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
