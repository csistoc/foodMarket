package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.service.EmployeeService;
import com.example.FoodMarket.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
//localhost:8080/user
public class UserController {

    private final UserService userService;
    private final EmployeeService employeeService;

    public UserController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<UserDefaultDto> getAllUsers() {
        return userService.getAllUsersAsDefaultDto();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDefaultDto>> createUser(@RequestBody UserCreateDto userCreateDto) {
        ApiResponse<UserDefaultDto> apiResponse = userService.addUserFromDto(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDefaultDto>> updateUser(
            @PathVariable Long id,
            @RequestBody UserDefaultDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        ApiResponse<UserDefaultDto> apiResponse = userService.updateUser(id, userDto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        ApiResponse<Void> apiResponse = userService.deleteUserById(id);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.noContent().build(); // 204
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
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
