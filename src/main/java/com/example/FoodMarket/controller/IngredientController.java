package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.model.Category;
import com.example.FoodMarket.model.Ingredient;
import com.example.FoodMarket.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ingredient")
@RestController
//localhost:8080/ingredient
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public List<IngredientDefaultDto> getAllIngredients() {
        return ingredientService.getAllIngredientsAsDefaultDto();
    }

    @PostMapping("/add")
    public Ingredient createCategory(@RequestBody IngredientCreateDto ingredientCreateDto) {
        return ingredientService.addIngredientFromDto(ingredientCreateDto);
    }

    @PutMapping("/changeName/{id}")
    public ResponseEntity<IngredientNameDto> updateName(
            @PathVariable Long id,
            @RequestBody IngredientNameDto ingredientNameDto) {

        ingredientNameDto.setId(id);  // ensure path ID and DTO ID are in sync
        Ingredient updatedIngredient = ingredientService.changeIngredientName(ingredientNameDto);

        // Map entity to DTO (you can use a mapper method/service here)
        IngredientNameDto responseDto = new IngredientNameDto();

        responseDto.setName(updatedIngredient.getName());

        return ResponseEntity.ok(responseDto);
    }
}
