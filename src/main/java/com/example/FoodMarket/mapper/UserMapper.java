package com.example.FoodMarket.mapper;

import com.example.FoodMarket.dto.UserCreateDto;
import com.example.FoodMarket.dto.UserDefaultDto;
import com.example.FoodMarket.model.Order;
import com.example.FoodMarket.model.Seller;
import com.example.FoodMarket.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // Convert Entity → DTO, including related IDs
    public UserDefaultDto convertToDefaultDto(User user) {
        Set<Long> orderIds = user.getOrders() != null
                ? user.getOrders().stream().map(Order::getId).collect(Collectors.toSet())
                : new HashSet<>();

        Set<Long> sellerIds = user.getSellers() != null
                ? user.getSellers().stream().map(Seller::getId).collect(Collectors.toSet())
                : new HashSet<>();

        return new UserDefaultDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getAddress(),
                user.getPhone(),
                user.getDateOfBirth(),
                orderIds,
                sellerIds
        );
    }

    // Convert DTO → Entity with orderIds and sellerIds
    public User convertFromDefaultDto(UserDefaultDto dto, Set<Order> orders, Set<Seller> sellers) {
        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getDateOfBirth(),
                orders,
                sellers
        );
    }

    // Convert DTO → Entity without orderIds and sellerIds
    public User convertFromDefaultDto(UserDefaultDto dto) {
        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getDateOfBirth()
        );
    }

    // Convert DTO → Entity without orderIds and sellerIds
    public User convertFromCreateDto(UserCreateDto dto) {
        return new User(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getDateOfBirth()
        );
    }
}
