package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.IngredientCreateDto;
import com.example.FoodMarket.dto.IngredientDefaultDto;
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

    public IngredientService(IngredientRepository ingredientRepository, ProductRepository productRepository) {
        this.ingredientRepository = ingredientRepository;
        this.productRepository = productRepository;
    }

    public IngredientDefaultDto convertToDefaultDto(Ingredient ingredient) {

        IngredientDefaultDto ingredientDefaultDto = new IngredientDefaultDto();

        ingredientDefaultDto.setId(ingredient.getId());
        ingredientDefaultDto.setName(ingredient.getName());

        Set<Long> productIds = new HashSet<>();
        for(Product i : ingredient.getProducts())
            productIds.add(i.getId());

        ingredientDefaultDto.setProductIds(productIds);

        return ingredientDefaultDto;
    }

    public List<IngredientDefaultDto> getAllIngredientsAsDefaultDto() {
        return ((List<Ingredient>) ingredientRepository.findAll())
                .stream()
                .map(this::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public Ingredient addIngredientFromDto(IngredientCreateDto ingredientCreateDto) {

        Ingredient ingredient = new Ingredient();

        ingredient.setName(ingredientCreateDto.getName());

        Set<Product> products = new HashSet<>();
        for (Long productId : ingredientCreateDto.getProductIds()) {
            Optional<Product> productOpt = productRepository.findById(productId);
            productOpt.ifPresent(products::add);
        }

        ingredient.setProducts(products);

        return ingredientRepository.save(ingredient);
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

        return new ApiResponse<>(true, "Category updated successfully.", convertToDefaultDto(ingredient));
    }

    public ApiResponse<Void> deleteIngredientById(Long id) {
        if (!ingredientRepository.existsById(id)) {
            return new ApiResponse<>(false, "Category not found with ID: " + id);
        }

        ingredientRepository.deleteById(id);

        return new ApiResponse<>(true, "Category deleted successfully.");
    }
}
