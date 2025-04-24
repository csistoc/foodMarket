package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
