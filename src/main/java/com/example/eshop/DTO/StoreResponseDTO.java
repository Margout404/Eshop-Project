package com.example.eshop.DTO;

import java.util.List;

public record StoreResponseDTO(
        int afm,
        String storeName,
        String owner,
        List<ItemResponseDTO> items
) {
}
