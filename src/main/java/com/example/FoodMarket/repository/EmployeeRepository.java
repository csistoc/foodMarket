package com.example.FoodMarket.repository;

import com.example.FoodMarket.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
