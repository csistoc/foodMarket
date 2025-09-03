package com.example.FoodMarket.mapper;

import com.example.FoodMarket.dto.SellerCreateDto;
import com.example.FoodMarket.dto.SellerDefaultDto;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.model.Seller;
import com.example.FoodMarket.model.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SellerMapper {

    // Convert Entity → DTO, including related IDs
    public SellerDefaultDto convertToDefaultDto(Seller seller) {
        Set<Long> userIds = seller.getUsers() != null
                ? seller.getUsers().stream().map(User::getId).collect(Collectors.toSet())
                : new HashSet<>();

        Set<Long> productIds = seller.getProducts() != null
                ? seller.getProducts().stream().map(Product::getId).collect(Collectors.toSet())
                : new HashSet<>();

        return new SellerDefaultDto(
                seller.getId(),
                seller.getName(),
                seller.getAddress(),
                seller.getPhone(),
                productIds,
                userIds
        );
    }

    // Convert DTO → Entity with orderIds and sellerIds
    public Seller convertFromDefaultDto(SellerDefaultDto dto, Set<Product> products, Set<User> users) {
        return new Seller(
                dto.getName(),
                dto.getAddress(),
                dto.getPhone(),
                products,
                users
        );
    }

    // Convert DTO → Entity without orderIds and sellerIds
    public Seller convertFromDefaultDto(SellerDefaultDto dto) {
        return new Seller(
                dto.getName(),
                dto.getAddress(),
                dto.getPhone()
        );
    }

    // Convert DTO → Entity without orderIds and sellerIds
    public Seller convertFromCreateDto(SellerCreateDto dto) {
        return new Seller(
                dto.getName(),
                dto.getAddress(),
                dto.getPhone()
        );
    }
}
