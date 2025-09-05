package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.mapper.OrderMapper;
import com.example.FoodMarket.model.Order;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.repository.OrderRepository;
import com.example.FoodMarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    public ApiResponse<OrderDefaultDto> addOrderFromDto(OrderCreateDto dto) {
        // Fetch related entities
        Set<Product> products = new HashSet<>(productRepository.findAllById(dto.getProductIds()));

        Order order = orderMapper.convertFromCreateDto(dto);
        order.setProducts(products);

        orderRepository.save(order);

        return new ApiResponse<>(true, "Order created successfully.", orderMapper.convertToDefaultDto(order));
    }

    public List<OrderDefaultDto> getAllOrdersAsDefaultDto() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public ApiResponse<OrderDefaultDto> updateOrder(Long id, OrderDefaultDto dto) {
        // Find order by ID
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isEmpty()) {
            return new ApiResponse<>(false, "Order not found with id " + id);
        }

        Order order = optionalOrder.get();

        // Update simple fields
        if (dto.getSeller() != null)
            order.setSeller(dto.getSeller());

        if (dto.getUser() != null)
            order.setUser(dto.getUser());

        if (dto.getOrderStatus() != null)
            order.setOrderStatus(dto.getOrderStatus());

        // Update products if provided
        if (dto.getProductIds() != null && !dto.getProductIds().isEmpty()) {
            Iterable<Product> foundProducts = productRepository.findAllById(dto.getProductIds());
            Set<Product> products = new HashSet<>();
            foundProducts.forEach(products::add);
            order.setProducts(products);
        }

        orderRepository.save(order);

        return new ApiResponse<>(true, "Order updated successfully.", orderMapper.convertToDefaultDto(order));
    }

    public ApiResponse<OrderDefaultDto> addProductsToOrder(Long id, OrderAddRemoveProduct dto) {
        // Fetch existing
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            return new ApiResponse<>(false, "Order not found with ID: " + id);
        }

        Order order = optionalOrder.get();

        Set<Product> products = new HashSet<>();
        for (Long productId : dto.getProductIds()) {
            Optional<Product> productOpt = productRepository.findById(productId);
            productOpt.ifPresent(products::add);
        }
        order.setProducts(products);

        orderRepository.save(order);

        return new ApiResponse<>(true, "Product added to order successfully.",
                orderMapper.convertToDefaultDto(order));
    }

    public ApiResponse<OrderDefaultDto> removeProductsFromOrder(Long id, OrderAddRemoveProduct dto) {
        // Fetch existing
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            return new ApiResponse<>(false, "Order not found with ID: " + id);
        }

        Order order = optionalOrder.get();

        Set<Product> products = new HashSet<>();
        for (Long productId : dto.getProductIds()) {
            Optional<Product> productOpt = productRepository.findById(productId);
            productOpt.ifPresent(products::remove);
        }
        order.setProducts(products);

        orderRepository.save(order);

        return new ApiResponse<>(true, "Product removed from order successfully.",
                orderMapper.convertToDefaultDto(order));
    }

    public ApiResponse<Void> deleteOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            return new ApiResponse<>(false, "Order not found with ID: " + id);
        }

        orderRepository.deleteById(id);

        return new ApiResponse<>(true, "Order deleted successfully.");
    }
}
