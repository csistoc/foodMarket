package com.example.FoodMarket.service;

import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.model.Order;
import com.example.FoodMarket.model.Product;
import com.example.FoodMarket.model.Seller;
import com.example.FoodMarket.model.User;
import com.example.FoodMarket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDefaultDto convertToDefaultDto(User user) {
        UserDefaultDto userDefaultDto = new UserDefaultDto();

        userDefaultDto.setId(user.getId());
        userDefaultDto.setUsername(user.getUsername());
        userDefaultDto.setPassword(user.getPassword());
        userDefaultDto.setEmail(user.getEmail());
        userDefaultDto.setFirstName(user.getFirstName());
        userDefaultDto.setLastName(user.getLastName());
        userDefaultDto.setAddress(user.getAddress());
        userDefaultDto.setPhone(user.getPhone());
        userDefaultDto.setDateOfBirth(user.getDateOfBirth());

        Set<Long> orderIds = new HashSet<>();
        for(Order i : user.getOrders())
            orderIds.add(i.getId());
        userDefaultDto.setOrderIds(orderIds);

        Set<Long> sellerIds = new HashSet<>();
        for(Seller i : user.getSellers())
            sellerIds.add(i.getId());
        userDefaultDto.setSellerIds(sellerIds);

        return userDefaultDto;
    }

    public User addUserFromDto(UserCreateDto userCreateDto) {
        // Optionally hash the password here
        User user = new User(
                userCreateDto.getUsername(),
                userCreateDto.getPassword(),
                userCreateDto.getEmail(),
                userCreateDto.getFirstName(),
                userCreateDto.getLastName(),
                userCreateDto.getAddress(),
                userCreateDto.getPhone(),
                userCreateDto.getDateOfBirth()
        );
        return userRepository.save(user);
    }

    public List<UserDefaultDto> getAllUsersAsDefaultDto() {
        return ((List<User>)userRepository.findAll())
                .stream()
                .map(this::convertToDefaultDto)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsersRaw() {
        return (List<User>)userRepository.findAll();
    }

    public UserDefaultDto getUserByIdAsDto(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDefaultDto)
                .orElse(null);
    }

    public User changeUserPassword(UserPasswordDto userPasswordDto) {
        // Fetch existing user
        Optional<User> optionalUser = userRepository.findById(userPasswordDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userPasswordDto.getId());
        }

        User user = optionalUser.get();

        // Update fields
        user.setPassword(userPasswordDto.getPassword());

        // Save updated user
        return userRepository.save(user);
    }

    public User changeUserUsername(UserUsernameDto userUsernameDto) {
        // Fetch existing user
        Optional<User> optionalUser = userRepository.findById(userUsernameDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userUsernameDto.getId());
        }

        User user = optionalUser.get();

        // Update fields
        user.setPassword(userUsernameDto.getUsername());

        // Save updated user
        return userRepository.save(user);
    }

    public User changeUserEmail(UserEmailDto userEmailDto) {
        // Fetch existing user
        Optional<User> optionalUser = userRepository.findById(userEmailDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userEmailDto.getId());
        }

        User user = optionalUser.get();

        // Update fields
        user.setEmail(userEmailDto.getEmail());

        // Save updated user
        return userRepository.save(user);
    }

    public User changeUserFirstName(UserFirstNameDto userFirstNameDto) {
        // Fetch existing user
        Optional<User> optionalUser = userRepository.findById(userFirstNameDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userFirstNameDto.getId());
        }

        User user = optionalUser.get();

        // Update fields
        user.setFirstName(userFirstNameDto.getFirstName());

        // Save updated user
        return userRepository.save(user);
    }

    public User changeUserLastName(UserLastNameDto userLastNameDto) {
        // Fetch existing user
        Optional<User> optionalUser = userRepository.findById(userLastNameDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userLastNameDto.getId());
        }

        User user = optionalUser.get();

        // Update fields
        user.setLastName(userLastNameDto.getLastName());

        // Save updated user
        return userRepository.save(user);
    }

    public User changeUserAddress(UserAddressDto userAddressDto) {
        // Fetch existing user
        Optional<User> optionalUser = userRepository.findById(userAddressDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userAddressDto.getId());
        }

        User user = optionalUser.get();

        // Update fields
        user.setAddress(userAddressDto.getAddress());

        // Save updated user
        return userRepository.save(user);
    }

    public User changeUserPhone(UserPhoneDto userPhoneDto) {
        // Fetch existing user
        Optional<User> optionalUser = userRepository.findById(userPhoneDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userPhoneDto.getId());
        }

        User user = optionalUser.get();

        // Update fields
        user.setPhone(userPhoneDto.getPhone());

        // Save updated user
        return userRepository.save(user);
    }

    public User changeDateOfBirth(UserDateOfBirthDto userDateOfBirthDto) {
        // Fetch existing user
        Optional<User> optionalUser = userRepository.findById(userDateOfBirthDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userDateOfBirthDto.getId());
        }

        User user = optionalUser.get();

        // Update fields
        user.setDateOfBirth(userDateOfBirthDto.getDateOfBirth());

        // Save updated user
        return userRepository.save(user);
    }

    /*
    public User deleteUserById(UserDefaultDto userDefaultDto) {

        Optional<User> optionalUser = userRepository.findById(userDefaultDto.getId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userDefaultDto.getId());
        }

        User user = optionalUser.get();
        userRepository.deleteById(user.getId());

        // 3. Save updated user
        return userRepository.save(user);
    }
     */

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }

        userRepository.deleteById(id);
    }
}
