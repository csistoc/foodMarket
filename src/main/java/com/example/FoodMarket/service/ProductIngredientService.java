package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.ProductIngredientDto;
import com.example.FoodMarket.model.Ingredient;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.repository.IngredientRepository;
import com.example.FoodMarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductIngredientService {

    private final ProductRepository productRepository;

    private final IngredientRepository ingredientRepository;

    public ProductIngredientService(ProductRepository productRepository, IngredientRepository ingredientRepository) {
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public ApiResponse<Void> addIngredientToProduct(ProductIngredientDto dto) {
        Optional<Product> productOpt = productRepository.findById(dto.getProductId());
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(dto.getIngredientId());

        if (productOpt.isEmpty() || ingredientOpt.isEmpty()) {
            return new ApiResponse<>(false, "Product or Ingredient not found.");
        }

        Product product = productOpt.get();
        Ingredient ingredient = ingredientOpt.get();

        // Check if it's already linked
        if (product.getIngredients().contains(ingredient)) {
            return new ApiResponse<>(false, "Ingredient already in product.");
        }

        // Add to both sides
        product.getIngredients().add(ingredient);
        ingredient.getProducts().add(product);

        productRepository.save(product);
        ingredientRepository.save(ingredient);

        return new ApiResponse<>(true,"Ingredient added to product successfully.");
    }

    public ApiResponse<Void> removeIngredientFromProduct(ProductIngredientDto dto) {
        Optional<Product> productOpt = productRepository.findById(dto.getProductId());
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(dto.getIngredientId());

        if (productOpt.isEmpty() || ingredientOpt.isEmpty()) {
            return new ApiResponse<>(false, "Product or Ingredient not found.");
        }

        Product product = productOpt.get();
        Ingredient ingredient = ingredientOpt.get();

        // Check if the ingredient is actually linked to the product
        if (!product.getIngredients().contains(ingredient)) {
            return new ApiResponse<>(false, "This ingredient is not in the product.");
        }

        // Remove from both sides of the relationship
        product.getIngredients().remove(ingredient);
        ingredient.getProducts().remove(product);

        productRepository.save(product);
        ingredientRepository.save(ingredient);

        return new ApiResponse<>(true, "Ingredient removed from product successfully.");
    }
}
