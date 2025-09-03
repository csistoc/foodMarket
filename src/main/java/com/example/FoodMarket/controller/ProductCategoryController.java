package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.ProductCategoryDto;
import com.example.FoodMarket.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product-category")
@RestController
//localhost:8080/product-category
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProductToCategory(@RequestBody ProductCategoryDto dto) {
        ApiResponse<Void> apiResponse = productCategoryService.addProductToCategory(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/products")
    public ResponseEntity<String> removeProductFromCategory(@RequestBody ProductCategoryDto dto) {
        ApiResponse<Void> apiResponse = productCategoryService.removeProductFromCategory(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }
}
