package com.example.FoodMarket.service;

import com.example.FoodMarket.dto.CategoryCreateDto;
import com.example.FoodMarket.dto.CategoryDefaultDto;
import com.example.FoodMarket.dto.CategoryNameDto;
import com.example.FoodMarket.dto.UserPasswordDto;
import com.example.FoodMarket.model.Category;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.model.User;
import com.example.FoodMarket.repository.CategoryRepository;
import com.example.FoodMarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

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

    public Category addCategoryFromDto(CategoryCreateDto categoryCreateDto) {

        Category category = new Category();

        category.setName(categoryCreateDto.getName());

        Set<Product> products = new HashSet<>();
        for (Long productId : categoryCreateDto.getProductIds()) {
            Optional<Product> productOpt = productRepository.findById(productId);
            productOpt.ifPresent(products::add);
        }

        category.setProducts(products);

        return categoryRepository.save(category);
    }

    public Category changeCategoryName(CategoryNameDto categoryNameDto) {
        // Fetch existing
        Optional<Category> optionalCategory = categoryRepository.findById(categoryNameDto.getId());
        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + categoryNameDto.getId());
        }

        Category category = optionalCategory.get();

        // Update fields
        category.setName(categoryNameDto.getName());

        return categoryRepository.save(category);
    }
}
