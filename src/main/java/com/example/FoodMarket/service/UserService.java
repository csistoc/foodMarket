package com.example.FoodMarket.service;

import com.example.FoodMarket.model.User;
import com.example.FoodMarket.dto.DefaultUserDto;
import com.example.FoodMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUserFromDto(DefaultUserDto userDto) {
        // Optionally hash the password here
        User user = new User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getAddress(),
                userDto.getDateOfBirth(),
                false,
                false
        );
        return userRepository.save(user);
    }

}
