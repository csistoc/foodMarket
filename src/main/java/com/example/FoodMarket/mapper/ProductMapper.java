package com.example.FoodMarket.mapper;

import com.example.FoodMarket.dto.ProductCreateDto;
import com.example.FoodMarket.dto.ProductDefaultDto;
import com.example.FoodMarket.model.*;

import java.util.HashSet;
import java.util.Set;

public class ProductMapper {

    // Convert Entity → DTO, including related IDs
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

    // Convert DTO → Entity with orderIds and sellerIds
    public Product convertFromDefaultDto(ProductDefaultDto dto, Set<Category> categories, Set<Ingredient> ingredients,
                                         Set<Seller> sellers, Set<Order> orders) {
        return new Product(
                dto.getId(),
                dto.getName(),
                categories,
                ingredients,
                sellers,
                orders
        );
    }

    // Convert DTO → Entity without Ids
    public Product convertFromDefaultDto(ProductDefaultDto dto) {
        return new Product(
                dto.getId(),
                dto.getName()
        );
    }

    // Convert DTO → Entity without Ids
    public Product convertFromCreateDto(ProductCreateDto dto) {
        return new Product(
                dto.getName()
        );
    }
}
