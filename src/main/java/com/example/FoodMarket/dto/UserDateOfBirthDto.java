package com.example.FoodMarket.dto;

import java.time.LocalDate;

public class UserDateOfBirthDto {

    private Long id;

    private LocalDate dateOfBirth;

    public UserDateOfBirthDto() {
    }

    public UserDateOfBirthDto(Long id, LocalDate dateOfBirth) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
