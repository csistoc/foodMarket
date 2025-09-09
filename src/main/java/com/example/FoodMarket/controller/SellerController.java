package com.example.FoodMarket.controller;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.SellerCreateDto;
import com.example.FoodMarket.dto.SellerDefaultDto;
import com.example.FoodMarket.dto.SellerProductDto;
import com.example.FoodMarket.service.SellerProductService;
import com.example.FoodMarket.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/seller")
@RestController
//localhost:8080/seller
public class SellerController {

    private final SellerService sellerService;
    private final SellerProductService sellerProductService;

    public SellerController(SellerService sellerService, SellerProductService sellerProductService) {
        this.sellerService = sellerService;
        this.sellerProductService = sellerProductService;
    }

    @GetMapping
    public List<SellerDefaultDto> getAllSellers() {
        return sellerService.getAllSellersAsDefaultDto();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SellerDefaultDto>> createUser(@RequestBody SellerCreateDto dto) {
        ApiResponse<SellerDefaultDto> apiResponse = sellerService.addSellerFromDto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SellerDefaultDto>> updateSeller(
            @PathVariable Long id,
            @RequestBody SellerDefaultDto dto) {

        dto.setId(id);  // ensure path ID and DTO ID are in sync
        ApiResponse<SellerDefaultDto> apiResponse = sellerService.updateSeller(id, dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeller(@PathVariable Long id) {
        ApiResponse<Void> apiResponse = sellerService.deleteSellerById(id);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.noContent().build(); // 204
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(apiResponse);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProductToSeller(@RequestBody SellerProductDto dto) {
        ApiResponse<Void> apiResponse = sellerProductService.addProductToSeller(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }

    @DeleteMapping("/products")
    public ResponseEntity<String> removeProductFromSeller(@RequestBody SellerProductDto dto) {
        ApiResponse<Void> apiResponse = sellerProductService.removeProductFromSeller(dto);

        if (apiResponse.isSuccess()) {
            return ResponseEntity.ok(apiResponse.getMessage());
        }
        else {
            return ResponseEntity.badRequest().body(apiResponse.getMessage());
        }
    }
}
