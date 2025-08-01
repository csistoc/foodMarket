package com.example.FoodMarket.controller;

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

    @PostMapping("/add")
    public ResponseEntity<String> addIngredientToProduct(@RequestBody ProductIngredientDto dto) {
        String result = productIngredientService.addIngredientToProduct(dto);
        return result.startsWith("Ingredient added")
                ? ResponseEntity.ok(result)
                : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeIngredientFromProduct(@RequestBody ProductIngredientDto dto) {
        String result = productIngredientService.removeIngredientFromProduct(dto);

        if (result.startsWith("Ingredient removed")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
