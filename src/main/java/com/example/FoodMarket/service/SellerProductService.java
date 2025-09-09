package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.SellerProductDto;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.model.Seller;
import com.example.FoodMarket.repository.ProductRepository;
import com.example.FoodMarket.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    public SellerProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public ApiResponse<Void> addProductToSeller(SellerProductDto dto) {
        Optional<Product> productOpt = productRepository.findById(dto.getProductId());
        Optional<Seller> sellerOpt = sellerRepository.findById(dto.getSellerId());

        if (productOpt.isEmpty() || sellerOpt.isEmpty()) {
            return new ApiResponse<>(false, "Product or Seller not found.");
        }

        Product product = productOpt.get();
        Seller seller = sellerOpt.get();

        // Check if it's already linked
        if (seller.getProducts().contains(product)) {
            return new ApiResponse<>(false, "Product already in seller.");
        }

        // Add to both sides
        product.getSellers().add(seller);
        seller.getProducts().add(product);

        productRepository.save(product);
        sellerRepository.save(seller);

        return new ApiResponse<>(true,"Product added to seller successfully.");
    }

    public ApiResponse<Void> removeProductFromSeller(SellerProductDto dto) {
        Optional<Product> productOpt = productRepository.findById(dto.getProductId());
        Optional<Seller> sellerOpt = sellerRepository.findById(dto.getSellerId());

        if (productOpt.isEmpty() || sellerOpt.isEmpty()) {
            return new ApiResponse<>(false, "Product or Seller not found.");
        }

        Product product = productOpt.get();
        Seller seller = sellerOpt.get();

        // Check if it's already linked
        if (seller.getProducts().contains(product)) {
            return new ApiResponse<>(false, "Product already in seller.");
        }

        // Add to both sides
        product.getSellers().remove(seller);
        seller.getProducts().remove(product);

        productRepository.save(product);
        sellerRepository.save(seller);

        return new ApiResponse<>(true, "Product removed from seller successfully.");
    }
}
