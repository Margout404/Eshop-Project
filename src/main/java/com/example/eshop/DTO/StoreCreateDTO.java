package com.example.eshop.DTO;

public record StoreCreateDTO(
        int afm,
        String storeName,
        String owner,
        String password
) {
}
