package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.SellerUsersDto;
import com.example.FoodMarket.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/employee")
@RestController
//localhost:8080/employee
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserToSellers(@RequestBody SellerUsersDto dto) {
        String result = employeeService.addUserToSeller(dto);

        if (result.startsWith("User added")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeUserFromSeller(@RequestBody SellerUsersDto dto) {
        String result = employeeService.removeUserFromSeller(dto);

        if (result.startsWith("User removed")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
