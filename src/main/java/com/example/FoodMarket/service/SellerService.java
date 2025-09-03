package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.SellerCreateDto;
import com.example.FoodMarket.dto.SellerDefaultDto;
import com.example.FoodMarket.mapper.SellerMapper;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.model.Seller;
import com.example.FoodMarket.model.User;
import com.example.FoodMarket.repository.ProductRepository;
import com.example.FoodMarket.repository.SellerRepository;
import com.example.FoodMarket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SellerMapper sellerMapper;

    public SellerService(SellerRepository sellerRepository, UserRepository userRepository, ProductRepository productRepository, SellerMapper sellerMapper) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.sellerMapper = sellerMapper;
    }

    public ApiResponse<SellerDefaultDto> addSellerFromDto(SellerCreateDto dto) {
        // Fetch related entities
        Set<User> users = new HashSet<>(userRepository.findAllById(dto.getUserIds()));
        Set<Product> products = new HashSet<>(productRepository.findAllById(dto.getProductIds()));

        Seller seller = sellerMapper.convertFromCreateDto(dto);
        seller.setUsers(users);
        seller.setProducts(products);

        sellerRepository.save(seller);

        return new ApiResponse<>(true, "Seller created successfully.", sellerMapper.convertToDefaultDto(seller));
    }

    public List<SellerDefaultDto> getAllSellersAsDefaultDto() {
        return sellerRepository.findAll()
                .stream()
                .map(sellerMapper::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public ApiResponse<SellerDefaultDto> updateSeller(Long id, SellerDefaultDto dto) {
        // Find seller by ID
        Optional<Seller> optionalSeller = sellerRepository.findById(id);

        if (optionalSeller.isEmpty()) {
            return new ApiResponse<>(false, "Seller not found with id " + id);
        }

        Seller seller = optionalSeller.get();

        // Update simple fields
        if (dto.getName() != null)
            seller.setName(dto.getName());

        if (dto.getAddress() != null)
            seller.setAddress(dto.getAddress());

        if (dto.getPhone() != null)
            seller.setPhone(dto.getPhone());

        // Update products if provided
        if (dto.getProductIds() != null && !dto.getProductIds().isEmpty()) {
            Iterable<Product> foundProducts = productRepository.findAllById(dto.getProductIds());
            Set<Product> products = new HashSet<>();
            foundProducts.forEach(products::add);
            seller.setProducts(products);
        }

        // Update users if provided
        if (dto.getUserIds() != null && !dto.getUserIds().isEmpty()) {
            Iterable<User> foundUsers = userRepository.findAllById(dto.getUserIds());
            Set<User> users = new HashSet<>();
            foundUsers.forEach(users::add);
            seller.setUsers(users);
        }

        sellerRepository.save(seller);

        return new ApiResponse<>(true, "Seller updated successfully.", sellerMapper.convertToDefaultDto(seller));
    }

    public ApiResponse<Void> deleteSellerById(Long id) {
        if (!sellerRepository.existsById(id)) {
            return new ApiResponse<>(false, "Seller not found with ID: " + id);
        }

        sellerRepository.deleteById(id);

        return new ApiResponse<>(true, "Seller deleted successfully.");
    }
}
