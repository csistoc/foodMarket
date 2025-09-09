package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
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

    @PostMapping("/sellers")
    public ResponseEntity<String> addUserToSeller(@RequestBody SellerUsersDto dto) {
        ApiResponse<Void> apiResponse = employeeService.addUserToSeller(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/sellers")
    public ResponseEntity<String> removeUserFromSeller(@RequestBody SellerUsersDto dto) {
        ApiResponse<Void> apiResponse = employeeService.removeUserFromSeller(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }
}
