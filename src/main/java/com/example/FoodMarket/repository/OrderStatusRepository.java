package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

}