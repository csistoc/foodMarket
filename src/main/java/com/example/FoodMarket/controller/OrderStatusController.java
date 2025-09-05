package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.service.OrderStatusService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<ApiResponse<OrderStatusDefaultDto>> createOrderStatus(
            @RequestBody OrderStatusCreateDto dto) {
        ApiResponse<OrderStatusDefaultDto> apiResponse = orderStatusService.addOrderStatusFromDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderStatusDefaultDto>> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusDefaultDto dto) {

        ApiResponse<OrderStatusDefaultDto> apiResponse = orderStatusService.updateOrderStatus(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrderToOrderStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusAddRemoveOrderDto dto) {

        ApiResponse<OrderStatusDefaultDto> apiResponse = orderStatusService.addOrderToOrderStatus(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/removeOrder")
    public ResponseEntity<String> removeOrderFromOrderStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusAddRemoveOrderDto dto) {

        ApiResponse<OrderStatusDefaultDto> apiResponse = orderStatusService.removeOrderFromOrderStatus(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderStatus(@PathVariable Long id) {
        ApiResponse<Void> apiResponse = orderStatusService.deleteOrderStatusById(id);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.noContent().build(); // 204
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }
}
