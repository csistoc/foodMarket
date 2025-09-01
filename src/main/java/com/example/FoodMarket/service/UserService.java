package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.mapper.UserMapper;
import com.example.FoodMarket.model.*;
import com.example.FoodMarket.repository.OrderRepository;
import com.example.FoodMarket.repository.SellerRepository;
import com.example.FoodMarket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final OrderRepository orderRepository;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper,
                       OrderRepository orderRepository,
                       SellerRepository sellerRepository,
                       UserRepository userRepository) {
        this.userMapper = userMapper;
        this.orderRepository = orderRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
    }

    public ApiResponse<UserDefaultDto> addUserFromDto(UserCreateDto dto) {
        // Fetch related entities
        Set<Order> orders = new HashSet<>(orderRepository.findAllById(dto.getOrderIds()));
        Set<Seller> sellers = new HashSet<>(sellerRepository.findAllById(dto.getSellerIds()));

        User user = userMapper.convertFromCreateDto(dto);
        user.setOrders(orders);
        user.setSellers(sellers);

        userRepository.save(user);

        return new ApiResponse<>(true, "User created successfully.", userMapper.convertToDefaultDto(user));
    }

    public List<UserDefaultDto> getAllUsersAsDefaultDto() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public UserDefaultDto getUserByIdAsDto(Long id) {
        return userRepository.findById(id)
                .map(userMapper::convertToDefaultDto)
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

        return new ApiResponse<>(true, "User updated successfully.", userMapper.convertToDefaultDto(user));
    }

    public ApiResponse<Void> deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            return new ApiResponse<>(false, "User not found with ID: " + id);
        }

        userRepository.deleteById(id);

        return new ApiResponse<>(true, "User deleted successfully.");
    }
}
