package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
