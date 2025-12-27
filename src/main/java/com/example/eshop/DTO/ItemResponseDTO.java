package com.example.eshop.DTO;

import java.math.BigDecimal;

public record ItemResponseDTO(
        Long itemId,
        String name,
        String brand,
        String description,
        BigDecimal price,
        int quantity
) {
}
