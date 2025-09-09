package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.ProductCreateDto;
import com.example.FoodMarket.dto.ProductDefaultDto;
import com.example.FoodMarket.dto.ProductIngredientDto;
import com.example.FoodMarket.service.ProductIngredientService;
import com.example.FoodMarket.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
//localhost:8080/product
public class ProductController {

    private final ProductService productService;
    private final ProductIngredientService productIngredientService;

    public ProductController(ProductService productService, ProductIngredientService productIngredientService) {
        this.productService = productService;
        this.productIngredientService = productIngredientService;
    }

    @GetMapping
    public List<ProductDefaultDto> getAllProducts() {
        return productService.getAllProductsAsDefaultDto();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDefaultDto>> createProduct(@RequestBody ProductCreateDto dto) {
        ApiResponse<ProductDefaultDto> apiResponse = productService.addProductFromDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDefaultDto>> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDefaultDto dto) {

        dto.setId(id);  // ensure path ID and DTO ID are in sync
        ApiResponse<ProductDefaultDto> apiResponse = productService.updateProduct(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        ApiResponse<Void> apiResponse = productService.deleteProductById(id);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.noContent().build(); // 204
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @PostMapping("/ingredients")
    public ResponseEntity<String> addIngredientToProduct(@RequestBody ProductIngredientDto dto) {
        ApiResponse<Void> apiResponse = productIngredientService.addIngredientToProduct(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/ingredients")
    public ResponseEntity<String> removeIngredientFromProduct(@RequestBody ProductIngredientDto dto) {
        ApiResponse<Void> apiResponse = productIngredientService.removeIngredientFromProduct(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }
}
