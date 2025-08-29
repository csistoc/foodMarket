package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
