package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.model.*;
import com.example.FoodMarket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDefaultDto convertToDefaultDto(User user) {
        UserDefaultDto userDefaultDto = new UserDefaultDto();

        userDefaultDto.setId(user.getId());
        userDefaultDto.setUsername(user.getUsername());
        userDefaultDto.setPassword(user.getPassword());
        userDefaultDto.setEmail(user.getEmail());
        userDefaultDto.setFirstName(user.getFirstName());
        userDefaultDto.setLastName(user.getLastName());
        userDefaultDto.setAddress(user.getAddress());
        userDefaultDto.setPhone(user.getPhone());
        userDefaultDto.setDateOfBirth(user.getDateOfBirth());

        Set<Long> orderIds = new HashSet<>();
        for(Order i : user.getOrders())
            orderIds.add(i.getId());
        userDefaultDto.setOrderIds(orderIds);

        Set<Long> sellerIds = new HashSet<>();
        for(Seller i : user.getSellers())
            sellerIds.add(i.getId());
        userDefaultDto.setSellerIds(sellerIds);

        return userDefaultDto;
    }

    public ApiResponse<UserDefaultDto> addUserFromDto(UserCleanDto userCleanDto) {
        // Optionally hash the password here
        User user = new User(
                userCleanDto.getUsername(),
                userCleanDto.getPassword(),
                userCleanDto.getEmail(),
                userCleanDto.getFirstName(),
                userCleanDto.getLastName(),
                userCleanDto.getAddress(),
                userCleanDto.getPhone(),
                userCleanDto.getDateOfBirth()
        );

        userRepository.save(user);

        UserDefaultDto userDefaultDto = convertToDefaultDto(user);

        return new ApiResponse<>(true, "User created successfully.", userDefaultDto);
    }

    public List<UserDefaultDto> getAllUsersAsDefaultDto() {
        return ((List<User>)userRepository.findAll())
                .stream()
                .map(this::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public UserDefaultDto getUserByIdAsDto(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDefaultDto)
                .orElse(null);
    }

    public ApiResponse<UserDefaultDto> updateUser(Long id, UserDefaultDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return new ApiResponse<>(false, "User not found with id " + id);
        }

        User user = optionalUser.get();

        if (userDto.getPassword() != null)
            user.setPassword(userDto.getPassword());

        if (userDto.getUsername() != null)
            user.setUsername(userDto.getUsername());

        if (userDto.getEmail() != null)
            user.setEmail(userDto.getEmail());

        if (userDto.getPhone() != null)
            user.setPhone(userDto.getPhone());

        if (userDto.getAddress() != null)
            user.setAddress(userDto.getAddress());

        if (userDto.getFirstName() != null)
            user.setFirstName(userDto.getFirstName());

        if (userDto.getLastName() != null)
            user.setLastName(userDto.getLastName());

        if (userDto.getDateOfBirth() != null)
            user.setDateOfBirth(userDto.getDateOfBirth());

        userRepository.save(user);

        UserDefaultDto outputUserDto = convertToDefaultDto(user);

        return new ApiResponse<>(true, "User updated successfully.", outputUserDto);
    }

    /*
    public User deleteUserById(UserDefaultDto userDefaultDto) {

        Optional<User> optionalUser = userRepository.findById(userDefaultDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userDefaultDto.getId());
        }

        User user = optionalUser.get();
        userRepository.deleteById(user.getId());

        // 3. Save updated user
        return userRepository.save(user);
    }
     */

    public ApiResponse<Void> deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            return new ApiResponse<>(false, "User not found with ID: " + id);
        }

        userRepository.deleteById(id);

        return new ApiResponse<>(true, "User deleted successfully.");
    }
}
