package com.example.FoodMarket.mapper;

import com.example.FoodMarket.dto.OrderCreateDto;
import com.example.FoodMarket.dto.OrderDefaultDto;
import com.example.FoodMarket.model.Order;
import com.example.FoodMarket.model.Product;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    // Convert Entity → DTO, including related IDs
    public OrderDefaultDto convertToDefaultDto(Order order) {
        Set<Long> productIds = order.getProducts() != null
                ? order.getProducts().stream().map(Product::getId).collect(Collectors.toSet())
                : new HashSet<>();

        return new OrderDefaultDto(
                order.getId(),
                order.getSeller(),
                order.getUser(),
                order.getOrderStatus(),
                productIds
        );
    }

    // Convert DTO → Entity with orderIds and sellerIds
    public Order convertFromDefaultDto(OrderDefaultDto dto, Set<Product> products) {
        return new Order(
                dto.getSeller(),
                dto.getUser(),
                dto.getOrderStatus(),
                products
        );
    }

    // Convert DTO → Entity without orderIds and sellerIds
    public Order convertFromDefaultDto(OrderDefaultDto dto) {
        return new Order(
                dto.getSeller(),
                dto.getUser(),
                dto.getOrderStatus()
        );
    }

    // Convert DTO → Entity without orderIds and sellerIds
    public Order convertFromCreateDto(OrderCreateDto dto) {
        return new Order(
                dto.getSeller(),
                dto.getUser(),
                dto.getOrderStatus()
        );
    }
}
