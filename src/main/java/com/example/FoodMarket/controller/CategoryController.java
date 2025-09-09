package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.CategoryCreateDto;
import com.example.FoodMarket.dto.CategoryDefaultDto;
import com.example.FoodMarket.dto.ProductCategoryDto;
import com.example.FoodMarket.service.CategoryService;
import com.example.FoodMarket.service.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
//localhost:8080/category
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductCategoryService productCategoryService;

    public CategoryController(CategoryService categoryService, ProductCategoryService productCategoryService) {
        this.categoryService = categoryService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<CategoryDefaultDto> getAllCategories() {
        return categoryService.getAllCategoriesAsDefaultDto();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDefaultDto>> createCategory(@RequestBody CategoryCreateDto dto) {
        ApiResponse<CategoryDefaultDto> apiResponse = categoryService.addCategoryFromDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDefaultDto>> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryDefaultDto dto) {

        ApiResponse<CategoryDefaultDto> apiResponse = categoryService.updateCategory(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        ApiResponse<Void> apiResponse = categoryService.deleteCategoryById(id);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.noContent().build(); // 204
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProductToCategory(@RequestBody ProductCategoryDto dto) {
        ApiResponse<Void> apiResponse = productCategoryService.addProductToCategory(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/products")
    public ResponseEntity<String> removeProductFromCategory(@RequestBody ProductCategoryDto dto) {
        ApiResponse<Void> apiResponse = productCategoryService.removeProductFromCategory(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }
}
