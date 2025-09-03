package com.example.FoodMarket.mapper;

import com.example.FoodMarket.dto.CategoryCreateDto;
import com.example.FoodMarket.dto.CategoryDefaultDto;
import com.example.FoodMarket.model.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryMapper {

    // Convert Entity → DTO, including related IDs
    public CategoryDefaultDto convertToDefaultDto(Category category) {
        Set<Long> productIds = category.getProducts() != null
                ? category.getProducts().stream().map(Product::getId).collect(Collectors.toSet())
                : new HashSet<>();

        return new CategoryDefaultDto(
                category.getId(),
                category.getName(),
                productIds
        );
    }

    // Convert DTO → Entity with productIds
    public Category convertFromDefaultDto(CategoryDefaultDto dto, Set<Product> products) {
        return new Category(
                dto.getName(),
                products
        );
    }

    // Convert DTO → Entity without productIds
    public Category convertFromDefaultDto(CategoryDefaultDto dto) {
        return new Category(dto.getName());
    }

    // Convert DTO → Entity without productIds
    public Category convertFromCreatetDto(CategoryCreateDto dto) {
        return new Category(dto.getName());
    }

}
