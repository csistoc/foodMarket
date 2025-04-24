package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {

}