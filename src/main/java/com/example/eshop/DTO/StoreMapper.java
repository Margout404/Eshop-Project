package com.example.eshop.DTO;

import com.example.eshop.model.Item;
import com.example.eshop.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreMapper {
    public static StoreResponseDTO toDTO(Store store) {

        List<ItemResponseDTO> items = (store.getItems() != null)
                ? store.getItems().stream().map(StoreMapper::toDTO).toList()
                : new ArrayList<>();

        return new StoreResponseDTO(
                store.getAfm(),
                store.getStoreName(),
                store.getOwner(),
                items
        );
    }

    public static ItemResponseDTO toDTO(Item item) {
        return new ItemResponseDTO(
                item.getItemId(),
                item.getName(),
                item.getBrand(),
                item.getDescription(),
                item.getPrice(),
                item.getQuantity()
        );
    }
}
