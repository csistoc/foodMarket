package com.example.FoodMarket.mapper;

import com.example.FoodMarket.dto.OrderStatusCreateDto;
import com.example.FoodMarket.dto.OrderStatusDefaultDto;
import com.example.FoodMarket.model.Order;
import com.example.FoodMarket.model.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderStatusMapper {

    // Convert Entity → DTO, including related IDs
    public OrderStatusDefaultDto convertToDefaultDto(OrderStatus orderStatus) {
        Set<Long> orderIds = orderStatus.getOrders() != null
                ? orderStatus.getOrders().stream().map(Order::getId).collect(Collectors.toSet())
                : new HashSet<>();

        return new OrderStatusDefaultDto(
                orderStatus.getId(),
                orderStatus.getName(),
                orderIds
        );
    }

    // Convert DTO → Entity with productIds
    public OrderStatus convertFromDefaultDto(OrderStatusDefaultDto dto, Set<Order> orders) {
        return new OrderStatus(
                dto.getName(),
                orders
        );
    }

    // Convert DTO → Entity without productIds
    public OrderStatus convertFromDefaultDto(OrderStatusDefaultDto dto) {
        return new OrderStatus(dto.getName());
    }

    // Convert DTO → Entity without productIds
    public OrderStatus convertFromCreatetDto(OrderStatusCreateDto dto) {
        return new OrderStatus(dto.getName());
    }

}
