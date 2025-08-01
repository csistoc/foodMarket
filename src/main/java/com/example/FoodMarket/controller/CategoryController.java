package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.CategoryCreateDto;
import com.example.FoodMarket.dto.CategoryDefaultDto;
import com.example.FoodMarket.dto.CategoryNameDto;
import com.example.FoodMarket.model.Category;
import com.example.FoodMarket.service.CategoryService;
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

    @PostMapping("/add")
    public Category createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        return categoryService.addCategoryFromDto(categoryCreateDto);
    }

    @PutMapping("/changeName/{id}")
    public ResponseEntity<CategoryNameDto> updateName(
            @PathVariable Long id,
            @RequestBody CategoryNameDto categoryNameDto) {

        categoryNameDto.setId(id);  // ensure path ID and DTO ID are in sync
        Category updatedCategory = categoryService.changeCategoryName(categoryNameDto);

        // Map entity to DTO (you can use a mapper method/service here)
        CategoryNameDto responseDto = new CategoryNameDto();

        responseDto.setName(updatedCategory.getName());

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok("Category with ID " + id + " deleted successfully.");
    }
}
