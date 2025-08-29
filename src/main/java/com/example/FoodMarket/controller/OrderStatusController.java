package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.model.OrderStatus;
import com.example.FoodMarket.service.OrderStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order-status")
@RestController
//localhost:8080/order-status
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping
    public List<OrderStatusDefaultDto> getAllOrderStatuses() {
        return orderStatusService.getAllOrderStatusesAsDefaultDto();
    }

    @PostMapping("/add")
    public OrderStatus createOrderStatus(@RequestBody OrderStatusCreateDto orderStatusCreateDto) {
        return orderStatusService.addOrderStatusFromDto(orderStatusCreateDto);
    }

    @PutMapping("/changeName/{id}")
    public ResponseEntity<OrderStatusNameDto> updateName(
            @PathVariable Long id,
            @RequestBody OrderStatusNameDto orderStatusNameDto) {

        orderStatusNameDto.setId(id);  // ensure path ID and DTO ID are in sync
        OrderStatus updatedOrderStatus = orderStatusService.changeOrderStatusName(orderStatusNameDto);

        // Map entity to DTO (you can use a mapper method/service here)
        OrderStatusNameDto responseDto = new OrderStatusNameDto();

        responseDto.setName(updatedOrderStatus.getName());

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrderToOrderStatus(@RequestBody OrderStatusOrderListDto dto) {
        String result = orderStatusService.addOrderToOrderStatus(dto);

        if (result.startsWith("Product added")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderStatus(@PathVariable Long id) {
        orderStatusService.deleteOrderStatusById(id);
        return ResponseEntity.ok("Order status with ID " + id + " deleted successfully.");
    }
}
