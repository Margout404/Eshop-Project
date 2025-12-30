package com.example.eshop.DTO.cartDTO;

import java.math.BigDecimal;
import java.util.List;

public record CartResponseDTO(
        Long itemId,
        int citizenAfm,
        double totalPrice,
        List<CartItemResponseDTO> items
) {
}
