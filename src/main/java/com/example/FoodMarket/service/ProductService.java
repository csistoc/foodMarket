package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.ProductCreateDto;
import com.example.FoodMarket.dto.ProductDefaultDto;
import com.example.FoodMarket.mapper.ProductMapper;
import com.example.FoodMarket.model.*;
import com.example.FoodMarket.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final SellerRepository sellerRepository;
    private final OrderRepository orderRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, IngredientRepository ingredientRepository, SellerRepository sellerRepository, OrderRepository orderRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.sellerRepository = sellerRepository;
        this.orderRepository = orderRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDefaultDto> getAllProductsAsDefaultDto() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public ApiResponse<ProductDefaultDto> addProductFromDto(ProductCreateDto dto) {
        Set<Order> orders = new HashSet<>(orderRepository.findAllById(dto.getOrderIds()));
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(dto.getCategoryIds()));
        Set<Ingredient> ingredients = new HashSet<>(ingredientRepository.findAllById(dto.getIngredientIds()));
        Set<Seller> sellers = new HashSet<>(sellerRepository.findAllById(dto.getSellerIds()));

        Product product = productMapper.convertFromCreateDto(dto);

        product.setCategories(categories);
        product.setIngredients(ingredients);
        product.setSellers(sellers);
        product.setOrders(orders);

        productRepository.save(product);

        return new ApiResponse<>(true, "Product added successfully.", productMapper.convertToDefaultDto(product));
    }

    public ApiResponse<ProductDefaultDto> updateProduct(Long id, ProductDefaultDto dto) {
        // Fetch existing
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            return new ApiResponse<>(false, "Product not found with ID: " + id);
        }

        Product product = optionalProduct.get();

        // Update simple fields
        if (dto.getName() != null)
            product.setName(dto.getName());

        // Update categories if provided
        if (dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
            Iterable<Category> foundCategories = categoryRepository.findAllById(dto.getCategoryIds());
            Set<Category> categories = new HashSet<>();
            foundCategories.forEach(categories::add);
            product.setCategories(categories);
        }

        // Update ingredients if provided
        if (dto.getIngredientIds() != null && !dto.getIngredientIds().isEmpty()) {
            Iterable<Ingredient> foundIngredients = ingredientRepository.findAllById(dto.getIngredientIds());
            Set<Ingredient> ingredients = new HashSet<>();
            foundIngredients.forEach(ingredients::add);
            product.setIngredients(ingredients);
        }

        // Update sellers if provided
        if (dto.getSellerIds() != null && !dto.getSellerIds().isEmpty()) {
            Iterable<Seller> foundSellers = sellerRepository.findAllById(dto.getSellerIds());
            Set<Seller> sellers = new HashSet<>();
            foundSellers.forEach(sellers::add);
            product.setSellers(sellers);
        }

        // Update orders if provided
        if (dto.getOrderIds() != null && !dto.getOrderIds().isEmpty()) {
            Iterable<Order> foundOrders = orderRepository.findAllById(dto.getOrderIds());
            Set<Order> orders = new HashSet<>();
            foundOrders.forEach(orders::add);
            product.setOrders(orders);
        }

        productRepository.save(product);

        return new ApiResponse<>(true, "Product updated successfully.", productMapper.convertToDefaultDto(product));
    }

    public ApiResponse<Void> deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            return new ApiResponse<>(false, "Product not found with ID: " + id);
        }

        productRepository.deleteById(id);

        return new ApiResponse<>(true, "Product deleted successfully.");
    }
}
