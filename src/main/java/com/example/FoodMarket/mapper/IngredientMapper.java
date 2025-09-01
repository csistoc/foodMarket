package com.example.FoodMarket.mapper;

import com.example.FoodMarket.dto.IngredientDefaultDto;
import com.example.FoodMarket.model.Ingredient;
import com.example.FoodMarket.model.Product;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class IngredientMapper {

    // Convert Entity → DTO, including related IDs
    public IngredientDefaultDto convertToDefaultDto(Ingredient ingredient) {
        Set<Long> productIds = ingredient.getProducts() != null
                ? ingredient.getProducts().stream().map(Product::getId).collect(Collectors.toSet())
                : new HashSet<>();

        return new IngredientDefaultDto(
                ingredient.getId(),
                ingredient.getName(),
                productIds
        );
    }

    // Convert DTO → Entity with productIds
    public Ingredient convertFromDefaultDto(IngredientDefaultDto dto, Set<Product> products) {
        return new Ingredient(
                dto.getName(),
                products
        );
    }

    // Convert DTO → Entity without productIds
    public Ingredient convertFromDefaultDto(IngredientDefaultDto dto) {
        return new Ingredient(dto.getName());
    }
}
