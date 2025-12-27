package com.example.eshop.DTO;

public record CitizenCreateDTO(
        int afm,
        String firstName,
        String lastName,
        String email,
        String password
) {
}
