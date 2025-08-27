package com.example.FoodMarket.service;

import com.example.FoodMarket.dto.*;
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

    public OrderStatusService(OrderStatusRepository orderStatusRepository, OrderRepository orderRepository) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderRepository = orderRepository;
    }

    public OrderStatusDefaultDto convertToDefaultDto(OrderStatus orderStatus) {

        OrderStatusDefaultDto orderStatusDefaultDto = new OrderStatusDefaultDto();

        orderStatusDefaultDto.setId(orderStatus.getId());
        orderStatusDefaultDto.setName(orderStatus.getName());

        Set<Long> orderIds = new HashSet<>();
        for(Order i : orderStatus.getOrders())
            orderIds.add(i.getId());
        orderStatusDefaultDto.setOrderIds(orderIds);

        return orderStatusDefaultDto;
    }

    public List<OrderStatusDefaultDto> getAllOrderStatusesAsDefaultDto() {
        return ((List<OrderStatus>)orderStatusRepository.findAll())
                .stream()
                .map(this::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public OrderStatus addOrderStatusFromDto(OrderStatusCleanDto orderStatusCleanDto) {

        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setName(orderStatusCleanDto.getName());

        Set<Order> orders = new HashSet<>();
        for (Long orderId : orderStatusCleanDto.getOrderIds()) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            orderOpt.ifPresent(orders::add);
        }
        orderStatus.setOrders(orders);

        return orderStatusRepository.save(orderStatus);
    }

    public OrderStatus changeOrderStatusName(OrderStatusNameDto orderStatusNameDto) {
        // Fetch existing
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(orderStatusNameDto.getId());
        if (optionalOrderStatus.isEmpty()) {
            throw new RuntimeException("Order status not found with ID: " + orderStatusNameDto.getId());
        }

        OrderStatus orderStatus = optionalOrderStatus.get();

        // Update fields
        orderStatus.setName(orderStatusNameDto.getName());

        return orderStatusRepository.save(orderStatus);
    }

    public String addOrderToOrderStatus(OrderStatusOrderListDto orderStatusOrderListDto) {
        // Fetch existing
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(orderStatusOrderListDto.getId());
        if (optionalOrderStatus.isEmpty()) {
            return "Order status not found with ID: " + orderStatusOrderListDto.getId();
        }

        OrderStatus orderStatus = optionalOrderStatus.get();

        Set<Order> orders = new HashSet<>();
        for (Long orderId : orderStatusOrderListDto.getOrderIds()) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            orderOpt.ifPresent(orders::add);
        }
        orderStatus.setOrders(orders);

        /*

            Add order status to order

         */

        orderStatusRepository.save(orderStatus);

        return "Order added to order status successfully.";
    }

    public OrderStatus removeOrderFromOrderStatus(OrderStatusOrderListDto orderStatusOrderListDto) {
        // Fetch existing
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(orderStatusOrderListDto.getId());
        if (optionalOrderStatus.isEmpty()) {
            throw new RuntimeException("Order status not found with ID: " + orderStatusOrderListDto.getId());
        }

        OrderStatus orderStatus = optionalOrderStatus.get();

        Set<Order> orders = new HashSet<>();
        for (Long orderId : orderStatusOrderListDto.getOrderIds()) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            orderOpt.ifPresent(orders::remove);
        }
        orderStatus.setOrders(orders);

        /*

            Remove order status from order

         */

        return orderStatusRepository.save(orderStatus);
    }

    public void deleteOrderStatusById(Long id) {
        if (!orderStatusRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }

        orderStatusRepository.deleteById(id);
    }
}
