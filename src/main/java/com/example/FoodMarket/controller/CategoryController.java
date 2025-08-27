package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.CategoryCleanDto;
import com.example.FoodMarket.dto.CategoryDefaultDto;
import com.example.FoodMarket.model.Category;
import com.example.FoodMarket.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
//localhost:8080/category
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDefaultDto> getAllCategories() {
        return categoryService.getAllCategoriesAsDefaultDto();
    }

    @PostMapping
    public ResponseEntity<CategoryDefaultDto> createCategory(@RequestBody CategoryCleanDto dto) {
        CategoryDefaultDto created = categoryService.addCategoryFromDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDefaultDto > updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryCleanDto dto) {

        CategoryDefaultDto updatedCategory = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
