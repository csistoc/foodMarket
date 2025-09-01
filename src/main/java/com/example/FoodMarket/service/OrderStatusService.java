package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.mapper.OrderStatusMapper;
import com.example.FoodMarket.model.*;
import com.example.FoodMarket.repository.OrderRepository;
import com.example.FoodMarket.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;
    private final OrderRepository orderRepository;

    private final OrderStatusMapper orderStatusMapper;

    public OrderStatusService(OrderStatusRepository orderStatusRepository, OrderRepository orderRepository, OrderStatusMapper orderStatusMapper) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderRepository = orderRepository;
        this.orderStatusMapper = orderStatusMapper;
    }

    public List<OrderStatusDefaultDto> getAllOrderStatusesAsDefaultDto() {
        return orderStatusRepository.findAll()
                .stream()
                .map(orderStatusMapper::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public ApiResponse<OrderStatusDefaultDto> addOrderStatusFromDto(OrderStatusCreateDto orderStatusCreateDto) {

        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setName(orderStatusCreateDto.getName());

        Set<Order> orders = new HashSet<>();
        for (Long orderId : orderStatusCreateDto.getOrderIds()) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            orderOpt.ifPresent(orders::add);
        }
        orderStatus.setOrders(orders);

        orderStatusRepository.save(orderStatus);

        return new ApiResponse<>(true, "Order status added successfully.", orderStatusMapper.convertToDefaultDto(orderStatus));
    }

    public ApiResponse<OrderStatusDefaultDto> updateOrderStatus(Long id, OrderStatusDefaultDto dto) {
        // Find category by ID
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(id);

        if (optionalOrderStatus.isEmpty()) {
            return new ApiResponse<>(false, "Order status not found.", dto);
        }

        OrderStatus orderStatus = optionalOrderStatus.get();

        // Update simple fields
        if (dto.getName() != null) {
            orderStatus.setName(dto.getName());
        }

        // Update products if provided
        if (dto.getOrderIds() != null && !dto.getOrderIds().isEmpty()) {
            Iterable<Order> foundOrders = orderRepository.findAllById(dto.getOrderIds());
            Set<Order> orders = new HashSet<>();
            foundOrders.forEach(orders::add);
            orderStatus.setOrders(orders);
        }

        orderStatusRepository.save(orderStatus);

        return new ApiResponse<>(true, "Order status updated successfully.", orderStatusMapper.convertToDefaultDto(orderStatus));
    }

    public ApiResponse<OrderStatusDefaultDto> addOrderToOrderStatus(Long id, OrderStatusAddRemoveOrderDto dto) {
        // Fetch existing
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(id);
        if (optionalOrderStatus.isEmpty()) {
            return new ApiResponse<>(false, "Order status not found with ID: " + id);
        }

        OrderStatus orderStatus = optionalOrderStatus.get();

        Set<Order> orders = new HashSet<>();
        for (Long orderId : dto.getOrderIds()) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            orderOpt.ifPresent(orders::add);
        }
        orderStatus.setOrders(orders);

        orderStatusRepository.save(orderStatus);

        return new ApiResponse<>(true, "Order added to order status successfully.",
                orderStatusMapper.convertToDefaultDto(orderStatus));
    }

    public ApiResponse<OrderStatusDefaultDto> removeOrderFromOrderStatus(Long id, OrderStatusAddRemoveOrderDto dto) {
        // Fetch existing
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(id);
        if (optionalOrderStatus.isEmpty()) {
            return new ApiResponse<>(false, "Order status not found with ID: " + id);
        }

        OrderStatus orderStatus = optionalOrderStatus.get();

        Set<Order> orders = new HashSet<>();
        for (Long orderId : dto.getOrderIds()) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            orderOpt.ifPresent(orders::remove);
        }
        orderStatus.setOrders(orders);

        orderStatusRepository.save(orderStatus);

        return new ApiResponse<>(true, "Order removed from order status successfully.",
                orderStatusMapper.convertToDefaultDto(orderStatus));
    }

    public ApiResponse<Void> deleteOrderStatusById(Long id) {
        if (!orderStatusRepository.existsById(id)) {
            return new ApiResponse<>(false, "Order status not found with ID: " + id);
        }

        orderStatusRepository.deleteById(id);

        return new ApiResponse<>(true, "Order status deleted successfully.");
    }
}
