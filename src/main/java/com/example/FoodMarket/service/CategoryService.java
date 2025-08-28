package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.CategoryCleanDto;
import com.example.FoodMarket.dto.CategoryDefaultDto;
import com.example.FoodMarket.model.Category;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.model.User;
import com.example.FoodMarket.repository.CategoryRepository;
import com.example.FoodMarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public CategoryDefaultDto convertToDefaultDto(Category category) {

        CategoryDefaultDto categoryDefaultDto = new CategoryDefaultDto();

        categoryDefaultDto.setId(category.getId());
        categoryDefaultDto.setName(category.getName());

        Set<Long> productIds = new HashSet<>();
        for(Product i : category.getProducts())
            productIds.add(i.getId());

        categoryDefaultDto.setProductIds(productIds);

        return categoryDefaultDto;
    }

    public List<CategoryDefaultDto> getAllCategoriesAsDefaultDto() {
        return ((List<Category>)categoryRepository.findAll())
                .stream()
                .map(this::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public ApiResponse<CategoryDefaultDto> addCategoryFromDto(CategoryCleanDto dto) {

        Category category = new Category();

        category.setName(dto.getName());

        Set<Product> products = new HashSet<>();
        for (Long productId : dto.getProductIds()) {
            Optional<Product> productOpt = productRepository.findById(productId);
            productOpt.ifPresent(products::add);
        }

        category.setProducts(products);

        return new ApiResponse<>(true, "Category added successfully.", convertToDefaultDto(categoryRepository.save(category)));
    }

    public ApiResponse<CategoryDefaultDto> updateCategory(Long id, CategoryDefaultDto dto) {
        // Find category by ID
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            return new ApiResponse<>(false, "Category not found.", dto);
        }

        Category category = optionalCategory.get();

        // Update simple fields
        if (dto.getName() != null) {
            category.setName(dto.getName());
        }

        // Update products if provided
        if (dto.getProductIds() != null && !dto.getProductIds().isEmpty()) {
            Iterable<Product> foundProducts = productRepository.findAllById(dto.getProductIds());
            Set<Product> products = new HashSet<>();
            foundProducts.forEach(products::add);
            category.setProducts(products);
        }

        categoryRepository.save(category);

        return new ApiResponse<>(true, "Category updated successfully.", convertToDefaultDto(categoryRepository.save(category)));
    }

    public ApiResponse<Void> deleteCategoryById(Long id) {

        if (!categoryRepository.existsById(id)) {
            return new ApiResponse<>(false, "Category not found with ID: " + id);
        }

        categoryRepository.deleteById(id);

        return new ApiResponse<>(true, "Category deleted successfully.");
    }
}
