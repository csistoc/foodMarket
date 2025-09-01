package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.IngredientCreateDto;
import com.example.FoodMarket.dto.IngredientDefaultDto;
import com.example.FoodMarket.mapper.IngredientMapper;
import com.example.FoodMarket.model.Ingredient;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.repository.IngredientRepository;
import com.example.FoodMarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ProductRepository productRepository;

    private final IngredientMapper ingredientMapper;

    public IngredientService(IngredientRepository ingredientRepository, ProductRepository productRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
        this.ingredientMapper = ingredientMapper;
    }

    public List<IngredientDefaultDto> getAllIngredientsAsDefaultDto() {
        return ingredientRepository.findAll()
                .stream()
                .map(ingredientMapper::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public ApiResponse<IngredientDefaultDto> addIngredientFromDto(IngredientCreateDto ingredientCreateDto) {

        Ingredient ingredient = new Ingredient();

        ingredient.setName(ingredientCreateDto.getName());

        Set<Product> products = new HashSet<>();
        for (Long productId : ingredientCreateDto.getProductIds()) {
            Optional<Product> productOpt = productRepository.findById(productId);
            productOpt.ifPresent(products::add);
        }

        ingredient.setProducts(products);

        ingredientRepository.save(ingredient);

        return new ApiResponse<>(true, "Ingredient added successfully.", ingredientMapper.convertToDefaultDto(ingredient));
    }

    public ApiResponse<IngredientDefaultDto> updateIngredient(Long id, IngredientDefaultDto dto) {
        // Find category by ID
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);

        if (optionalIngredient.isEmpty()) {
            return new ApiResponse<>(false, "Ingredient not found.", dto);
        }

        Ingredient ingredient = optionalIngredient.get();

        // Update simple fields
        if (dto.getName() != null) {
            ingredient.setName(dto.getName());
        }

        // Update products if provided
        if (dto.getProductIds() != null && !dto.getProductIds().isEmpty()) {
            Iterable<Product> foundProducts = productRepository.findAllById(dto.getProductIds());
            Set<Product> products = new HashSet<>();
            foundProducts.forEach(products::add);
            ingredient.setProducts(products);
        }

        ingredientRepository.save(ingredient);

        return new ApiResponse<>(true, "Ingredient updated successfully.", ingredientMapper.convertToDefaultDto(ingredient));
    }

    public ApiResponse<Void> deleteIngredientById(Long id) {
        if (!ingredientRepository.existsById(id)) {
            return new ApiResponse<>(false, "Category not found with ID: " + id);
        }

        ingredientRepository.deleteById(id);

        return new ApiResponse<>(true, "Category deleted successfully.");
    }
}
