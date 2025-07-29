package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.ProductCategoryDto;
import com.example.FoodMarket.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product-category")
@RestController
//localhost:8080/product-category
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addProductToCategory(@RequestBody ProductCategoryDto dto) {
        String result = productCategoryService.addProductToCategory(dto);

        if (result.startsWith("Product added")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeProductFromCategory(@RequestBody ProductCategoryDto dto) {
        String result = productCategoryService.removeProductFromCategory(dto);

        if (result.startsWith("Product removed")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
