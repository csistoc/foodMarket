package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
