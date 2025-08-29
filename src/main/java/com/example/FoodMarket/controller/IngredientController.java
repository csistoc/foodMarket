package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.IngredientCreateDto;
import com.example.FoodMarket.dto.IngredientDefaultDto;
import com.example.FoodMarket.model.Ingredient;
import com.example.FoodMarket.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ingredient")
@RestController
//localhost:8080/ingredient
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public List<IngredientDefaultDto> getAllIngredients() {
        return ingredientService.getAllIngredientsAsDefaultDto();
    }

    @PostMapping
    public Ingredient createCategory(@RequestBody IngredientCreateDto ingredientCreateDto) {
        return ingredientService.addIngredientFromDto(ingredientCreateDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IngredientDefaultDto>> updateIngredient(
            @PathVariable Long id,
            @RequestBody IngredientDefaultDto dto) {

        ApiResponse<IngredientDefaultDto> apiResponse = ingredientService.updateIngredient(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
        ApiResponse<Void> apiResponse = ingredientService.deleteIngredientById(id);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.noContent().build(); // 204
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }
}
