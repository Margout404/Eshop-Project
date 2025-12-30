package com.example.eshop.DTO.cartDTO;

import java.math.BigDecimal;

public record CartItemResponseDTO(
        Long itemId,
        String name,
        double price,
        int quantity
) {
}
