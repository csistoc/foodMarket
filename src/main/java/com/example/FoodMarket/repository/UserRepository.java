package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
