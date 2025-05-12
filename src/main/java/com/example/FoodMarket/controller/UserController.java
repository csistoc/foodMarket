package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.DefaultUserDto;
import com.example.FoodMarket.model.User;
import com.example.FoodMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
//localhost:8080/user
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User createUser(@RequestBody DefaultUserDto userDto) {
        return userService.addUserFromDto(userDto);
    }

}
