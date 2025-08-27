package com.example.FoodMarket.service;

import com.example.FoodMarket.dto.ProductCleanDto;
import com.example.FoodMarket.dto.ProductDefaultDto;
import com.example.FoodMarket.dto.ProductNameDto;
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

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, IngredientRepository ingredientRepository, SellerRepository sellerRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.sellerRepository = sellerRepository;
        this.orderRepository = orderRepository;
    }

    public ProductDefaultDto convertToDefaultDto(Product product) {

        ProductDefaultDto productDefaultDto = new ProductDefaultDto();

        productDefaultDto.setId(product.getId());
        productDefaultDto.setName(product.getName());

        Set<Long> categoryIds = new HashSet<>();
        for(Category i : product.getCategories())
            categoryIds.add(i.getId());
        productDefaultDto.setCategoryIds(categoryIds);

        Set<Long> ingredientIds = new HashSet<>();
        for(Ingredient i : product.getIngredients())
            ingredientIds.add(i.getId());
        productDefaultDto.setIngredientIds(ingredientIds);

        Set<Long> sellerIds = new HashSet<>();
        for(Seller i : product.getSellers())
            sellerIds.add(i.getId());
        productDefaultDto.setSellerIds(sellerIds);

        Set<Long> orderIds = new HashSet<>();
        for(Order i : product.getOrders())
            orderIds.add(i.getId());
        productDefaultDto.setOrderIds(orderIds);

        return productDefaultDto;
    }

    public List<ProductDefaultDto> getAllProductsAsDefaultDto() {
        return ((List<Product>)productRepository.findAll())
                .stream()
                .map(this::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public Product addProductFromDto(ProductCleanDto productCleanDto) {

        Product product = new Product();

        product.setName(productCleanDto.getName());

        Set<Category> categories = new HashSet<>();
        for (Long categoryId : productCleanDto.getCategoryIds()) {
            Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
            categoryOpt.ifPresent(categories::add);
        }
        product.setCategories(categories);

        Set<Ingredient> ingredients = new HashSet<>();
        for (Long ingredientId : productCleanDto.getIngredientIds()) {
            Optional<Ingredient> ingredientOpt = ingredientRepository.findById(ingredientId);
            ingredientOpt.ifPresent(ingredients::add);
        }
        product.setIngredients(ingredients);

        Set<Seller> sellers = new HashSet<>();
        for (Long sellerId : productCleanDto.getSellerIds()) {
            Optional<Seller> sellerOpt = sellerRepository.findById(sellerId);
            sellerOpt.ifPresent(sellers::add);
        }
        product.setSellers(sellers);

        Set<Order> orders = new HashSet<>();
        for (Long orderId : productCleanDto.getOrderIds()) {
            Optional<Order> orderOpt = orderRepository.findById(orderId);
            orderOpt.ifPresent(orders::add);
        }
        product.setOrders(orders);

        return productRepository.save(product);
    }

    public Product changeProductName(ProductNameDto productNameDto) {
        // Fetch existing
        Optional<Product> optionalProduct = productRepository.findById(productNameDto.getId());
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + productNameDto.getId());
        }

        Product product = optionalProduct.get();

        // Update fields
        product.setName(productNameDto.getName());

        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }

        productRepository.deleteById(id);
    }
}
