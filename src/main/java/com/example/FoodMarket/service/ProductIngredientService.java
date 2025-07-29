package com.example.FoodMarket.service;

import com.example.FoodMarket.dto.ProductIngredientDto;
import com.example.FoodMarket.model.Ingredient;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.repository.IngredientRepository;
import com.example.FoodMarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductIngredientService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public String addIngredientToProduct(ProductIngredientDto dto) {
        Optional<Product> productOpt = productRepository.findById(dto.getProductId());
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(dto.getIngredientId());

        if (productOpt.isEmpty() || ingredientOpt.isEmpty()) {
            return "Product or Ingredient not found.";
        }

        Product product = productOpt.get();
        Ingredient ingredient = ingredientOpt.get();

        // Check if it's already linked
        if (product.getIngredients().contains(ingredient)) {
            return "Ingredient already in product.";
        }

        // Add to both sides
        product.getIngredients().add(ingredient);
        ingredient.getProducts().add(product);

        productRepository.save(product); // Cascade saves join table entry
        return "Ingredient added to product successfully.";
    }

    public String removeIngredientFromProduct(ProductIngredientDto dto) {
        Optional<Product> productOpt = productRepository.findById(dto.getProductId());
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(dto.getIngredientId());

        if (productOpt.isEmpty() || ingredientOpt.isEmpty()) {
            return "Product or Ingredient not found.";
        }

        Product product = productOpt.get();
        Ingredient ingredient = ingredientOpt.get();

        // Check if the ingredient is actually linked to the product
        if (!product.getIngredients().contains(ingredient)) {
            return "This ingredient is not in the product.";
        }

        // Remove from both sides of the relationship
        product.getIngredients().remove(ingredient);
        ingredient.getProducts().remove(product);

        productRepository.save(product); // Cascade removes join table entry
        return "Ingredient removed from product successfully.";
    }
}
