package com.example.eshop.DTO;

import java.math.BigDecimal;

public record ItemCreateDTO(
        String name,
        String brand,
        String description,
        double price,
        int quantity
) {
}
