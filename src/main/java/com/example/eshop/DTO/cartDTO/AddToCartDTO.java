package com.example.eshop.DTO.cartDTO;

public record AddToCartDTO(
        int citizenAfm,
        Long itemId,
        int quantity
) {}
