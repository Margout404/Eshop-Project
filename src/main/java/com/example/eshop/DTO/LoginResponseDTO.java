package com.example.eshop.DTO;

public record LoginResponseDTO(
        boolean success,
        String message,
        String role,    // "CITIZEN" ή "STORE"
        int afm,
        String name)
{
}
