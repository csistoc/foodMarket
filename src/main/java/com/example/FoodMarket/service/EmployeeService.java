package com.example.FoodMarket.service;

import com.example.FoodMarket.api.ApiResponse;
import com.example.FoodMarket.dto.SellerUsersDto;
import com.example.FoodMarket.model.*;
import com.example.FoodMarket.repository.SellerRepository;
import com.example.FoodMarket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    //private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;

    public EmployeeService(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    /*

    public EmployeeDefaultDto convertToDefaultDto(Employee employee) {
        return new EmployeeDefaultDto(
                employee.getId(),
                employee.getUser(),
                employee.getSeller()
        );
    }

    public Employee addEmployeeFromDto(EmployeeCreateDto employeeCreateDto) {
        Employee employee = new Employee(
                employeeCreateDto.getUser(),
                employeeCreateDto.getSeller()
        );
        return employeeRepository.save(employee);
    }

    public List<EmployeeDefaultDto> getAllEmployeesAsDefaultDto() {
        return ((List<Employee>)employeeRepository.findAll())
                .stream()
                .map(this::convertToDefaultDto)
                .collect(Collectors.toList());
    }

     */

    public ApiResponse<Void> addUserToSeller(SellerUsersDto dto) {
        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        Optional<Seller> sellerOpt = sellerRepository.findById(dto.getSellerId());

        if (userOpt.isEmpty() || sellerOpt.isEmpty()) {
            return new ApiResponse<>(false, "Seller or User not found.");
        }

        User user = userOpt.get();
        Seller seller = sellerOpt.get();

        // Check if already related
        if (seller.getUsers().contains(user)) {
            return new ApiResponse<>(false, "This user is already in the sellers list.");
        }

        // Add relationship
        seller.getUsers().add(user);
        user.getSellers().add(seller);

        sellerRepository.save(seller);
        userRepository.save(user);

        return new ApiResponse<>(true, "User added to sellers successfully.");
    }

    public ApiResponse<Void> removeUserFromSeller(SellerUsersDto dto) {
        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        Optional<Seller> sellerOpt = sellerRepository.findById(dto.getSellerId());

        if (userOpt.isEmpty() || sellerOpt.isEmpty()) {
            return new ApiResponse<>(false,"Seller or User not found.");
        }

        User user = userOpt.get();
        Seller seller = sellerOpt.get();

        // Check if user is associated
        if (!seller.getUsers().contains(user)) {
            return new ApiResponse<>(false,"This user is not in the sellers list.");
        }

        // Remove relationship on both sides
        seller.getUsers().remove(user);
        user.getSellers().remove(seller);

        sellerRepository.save(seller);
        userRepository.save(user);

        return new ApiResponse<>(true,"User removed from sellers successfully.");
    }
}
