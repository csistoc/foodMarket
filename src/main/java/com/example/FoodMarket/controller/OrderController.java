package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
//localhost:8080/order
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDefaultDto> getAllOrders() {
        return orderService.getAllOrdersAsDefaultDto();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderDefaultDto>> createOrder(
            @RequestBody OrderCreateDto dto) {
        ApiResponse<OrderDefaultDto> apiResponse = orderService.addOrderFromDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDefaultDto>> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderDefaultDto dto) {

        ApiResponse<OrderDefaultDto> apiResponse = orderService.updateOrder(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProductsToOrder(
            @PathVariable Long id,
            @RequestBody OrderAddRemoveProduct dto) {

        ApiResponse<OrderDefaultDto> apiResponse = orderService.addProductsToOrder(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/removeProduct")
    public ResponseEntity<String> removeProductsFromOrder(
            @PathVariable Long id,
            @RequestBody OrderAddRemoveProduct dto) {

        ApiResponse<OrderDefaultDto> apiResponse = orderService.removeProductsFromOrder(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderStatus(@PathVariable Long id) {
        ApiResponse<Void> apiResponse = orderService.deleteOrderById(id);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.noContent().build(); // 204
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }
}
