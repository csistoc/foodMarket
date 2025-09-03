package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.ProductIngredientDto;
import com.example.FoodMarket.service.ProductIngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product-ingredient")
@RestController
//localhost:8080/product-ingredient
public class ProductIngredientController {

    private final ProductIngredientService productIngredientService;

    public ProductIngredientController(ProductIngredientService productIngredientService) {
        this.productIngredientService = productIngredientService;
    }

    @PostMapping("/ingredients")
    public ResponseEntity<String> addIngredientToProduct(@RequestBody ProductIngredientDto dto) {
        ApiResponse<Void> apiResponse = productIngredientService.addIngredientToProduct(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/ingredients")
    public ResponseEntity<String> removeIngredientFromProduct(@RequestBody ProductIngredientDto dto) {
        ApiResponse<Void> apiResponse = productIngredientService.removeIngredientFromProduct(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }
}
