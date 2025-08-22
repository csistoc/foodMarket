package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.ProductCreateDto;
import com.example.FoodMarket.dto.ProductDefaultDto;
import com.example.FoodMarket.dto.ProductNameDto;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
//localhost:8080/product
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDefaultDto> getAllProducts() {
        return productService.getAllProductsAsDefaultDto();
    }

    @PostMapping("/add")
    public Product createProduct(@RequestBody ProductCreateDto productCreateDto) {
        return productService.addProductFromDto(productCreateDto);
    }

    @PutMapping("/changeName/{id}")
    public ResponseEntity<ProductNameDto> updateName(
            @PathVariable Long id,
            @RequestBody ProductNameDto productNameDto) {

        productNameDto.setId(id);  // ensure path ID and DTO ID are in sync
        Product updatedProduct = productService.changeProductName(productNameDto);

        // Map entity to DTO (you can use a mapper method/service here)
        ProductNameDto responseDto = new ProductNameDto();

        responseDto.setName(updatedProduct.getName());

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product with ID " + id + " deleted successfully.");
    }
}
