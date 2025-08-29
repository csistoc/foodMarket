package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
