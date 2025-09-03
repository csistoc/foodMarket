package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.ProductCategoryDto;
import com.example.FoodMarket.model.Category;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.repository.CategoryRepository;
import com.example.FoodMarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductCategoryService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ApiResponse<Void> addProductToCategory(ProductCategoryDto dto) {
        Optional<Product> productOpt = productRepository.findById(dto.getProductId());
        Optional<Category> categoryOpt = categoryRepository.findById(dto.getCategoryId());

        if (productOpt.isEmpty() || categoryOpt.isEmpty()) {
            return new ApiResponse<>(false, "Product or Category not found.");
        }

        Product product = productOpt.get();
        Category category = categoryOpt.get();

        // Check if already related
        if (category.getProducts().contains(product)) {
            return new ApiResponse<>(false, "This product is already in the category.");
        }

        // Add relationship
        category.getProducts().add(product);
        product.getCategories().add(category);

        categoryRepository.save(category); // Cascade saves join table entry
        return new ApiResponse<>(true, "Product added to category successfully.");
    }

    public ApiResponse<Void> removeProductFromCategory(ProductCategoryDto dto) {
        Optional<Product> productOpt = productRepository.findById(dto.getProductId());
        Optional<Category> categoryOpt = categoryRepository.findById(dto.getCategoryId());

        if (productOpt.isEmpty() || categoryOpt.isEmpty()) {
            return new ApiResponse<>(false, "Product or Category not found.");
        }

        Product product = productOpt.get();
        Category category = categoryOpt.get();

        // Check if the product is in this category
        if (!category.getProducts().contains(product)) {
            return new ApiResponse<>(false, "This product is not in the category.");
        }

        // Remove relationship on both sides
        category.getProducts().remove(product);
        product.getCategories().remove(category);

        categoryRepository.save(category); // Cascade removes join table entry
        return new ApiResponse<>(true, "Product removed from category successfully.");
    }
}
