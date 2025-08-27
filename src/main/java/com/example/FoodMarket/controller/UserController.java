package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.model.User;
import com.example.FoodMarket.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
//localhost:8080/user
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDefaultDto> getAllUsers() {
        return userService.getAllUsersAsDefaultDto();
    }

    @PostMapping("/add")
    public User createUser(@RequestBody UserCleanDto userCleanDto) {
        return userService.addUserFromDto(userCleanDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDefaultDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserDefaultDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        User updatedUser = userService.updateUser(userDto);

        return ResponseEntity.ok(userService.convertToDefaultDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
    }
}
