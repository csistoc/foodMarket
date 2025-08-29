package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
