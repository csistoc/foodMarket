package com.example.FoodMarket.controller;

import com.example.FoodMarket.dto.*;
import com.example.FoodMarket.model.User;
import com.example.FoodMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
//localhost:8080/user
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDefaultDto> getAllUsers() {
        return userService.getAllUsersAsDefaultDto();
    }

    @GetMapping("/raw")
    public List<User> getAllUsersRaw() {
        return userService.getAllUsersRaw();
    }

    @PostMapping("/add")
    public User createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.addUserFromDto(userCreateDto);
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<UserPasswordDto> updatePassword(
            @PathVariable Long id,
            @RequestBody UserPasswordDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        User updatedUser = userService.changeUserPassword(userDto);

        // Map entity to DTO (you can use a mapper method/service here)
        UserPasswordDto responseDto = new UserPasswordDto();

        responseDto.setPassword(updatedUser.getPassword());

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/changeUsername/{id}")
    public ResponseEntity<UserUsernameDto> updateUsername(
            @PathVariable Long id,
            @RequestBody UserUsernameDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        User updatedUser = userService.changeUserUsername(userDto);

        // Map entity to DTO (you can use a mapper method/service here)
        UserUsernameDto responseDto = new UserUsernameDto();

        responseDto.setUsername(updatedUser.getUsername());

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/changeEmail/{id}")
    public ResponseEntity<UserEmailDto> updateEmail(
            @PathVariable Long id,
            @RequestBody UserEmailDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        User updatedUser = userService.changeUserEmail(userDto);

        // Map entity to DTO (you can use a mapper method/service here)
        UserEmailDto responseDto = new UserEmailDto();

        responseDto.setEmail(updatedUser.getEmail());

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/changeFirstName/{id}")
    public ResponseEntity<UserFirstNameDto> updateFirstName(
            @PathVariable Long id,
            @RequestBody UserFirstNameDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        User updatedUser = userService.changeUserFirstName(userDto);

        // Map entity to DTO (you can use a mapper method/service here)
        UserFirstNameDto responseDto = new UserFirstNameDto();

        responseDto.setFirstName(updatedUser.getFirstName());

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/changeLastName/{id}")
    public ResponseEntity<UserLastNameDto> updateLastName(
            @PathVariable Long id,
            @RequestBody UserLastNameDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        User updatedUser = userService.changeUserLastName(userDto);

        // Map entity to DTO (you can use a mapper method/service here)
        UserLastNameDto responseDto = new UserLastNameDto();

        responseDto.setLastName(updatedUser.getLastName());

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/changeAddress/{id}")
    public ResponseEntity<UserAddressDto> updateAddress(
            @PathVariable Long id,
            @RequestBody UserAddressDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        User updatedUser = userService.changeUserAddress(userDto);

        // Map entity to DTO (you can use a mapper method/service here)
        UserAddressDto responseDto = new UserAddressDto();

        responseDto.setAddress(updatedUser.getAddress());

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/changePhone/{id}")
    public ResponseEntity<UserPhoneDto> updatePhone(
            @PathVariable Long id,
            @RequestBody UserPhoneDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        User updatedUser = userService.changeUserPhone(userDto);

        // Map entity to DTO (you can use a mapper method/service here)
        UserPhoneDto responseDto = new UserPhoneDto();

        responseDto.setPhone(updatedUser.getPhone());

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/changeDateOfBirth/{id}")
    public ResponseEntity<UserDateOfBirthDto> updateDateOfBirth(
            @PathVariable Long id,
            @RequestBody UserDateOfBirthDto userDto) {

        userDto.setId(id);  // ensure path ID and DTO ID are in sync
        User updatedUser = userService.changeDateOfBirth(userDto);

        // Map entity to DTO (you can use a mapper method/service here)
        UserDateOfBirthDto responseDto = new UserDateOfBirthDto();

        responseDto.setDateOfBirth(updatedUser.getDateOfBirth());

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with ID " + id + " deleted successfully.");
    }

    /*
    @PutMapping("/changeData/{id}")
    public ResponseEntity<UserDateOfBirthDto> updateData(
            @PathVariable Long id,
            @RequestBody UserDefaultDto userDefaultDto) {

        userDefaultDto.setId(id);  // ensure path ID and DTO ID are in sync

        User updatedUser = new User();

        String tmp_str = userDefaultDto.getEmail();
        if (tmp_str != null && !tmp_str.isEmpty())
            updatedUser.setEmail(userService.changeUserEmail(new UserEmailDto(id, userDefaultDto.getEmail())).getEmail());

        tmp_str = userDefaultDto.getUsername();
        if (tmp_str != null && !tmp_str.isEmpty())
            updatedUser.setUsername(userService.changeUserUsername(new UserUsernameDto(id, userDefaultDto.getUsername())).getUsername());

        tmp_str = userDefaultDto.getPassword();
        if (tmp_str != null && !tmp_str.isEmpty())
            updatedUser.setPassword(userService.changeUserPassword(new UserPasswordDto(id, userDefaultDto.getPassword())).getPassword());

        tmp_str = userDefaultDto.getPhone();
        if (tmp_str != null && !tmp_str.isEmpty())
            updatedUser.setPhone(userService.changeUserPhone(new UserPhoneDto(id, userDefaultDto.getPhone())).getPhone());

        tmp_str = userDefaultDto.getAddress();
        if (tmp_str != null && !tmp_str.isEmpty())
            updatedUser.setAddress(userService.changeUserAddress(new UserAddressDto(id, userDefaultDto.getAddress())).getAddress());

        LocalDate tmp_date = userDefaultDto.getDateOfBirth();
        if (userDefaultDto.getDateOfBirth() != null)
            updatedUser.setEmail(userService.changeUserEmail(new UserEmailDto(id, userDefaultDto.getEmail())).getEmail());

        //User updatedUser = userService.changeDateOfBirth(userDto);

        // Map entity to DTO (you can use a mapper method/service here)
        UserDateOfBirthDto responseDto = new UserDateOfBirthDto();

        responseDto.setDateOfBirth(updatedUser.getDateOfBirth());

        return ResponseEntity.ok(responseDto);

    }
     */
}
