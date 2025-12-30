package com.example.eshop.DTO.cartDTO;

import com.example.eshop.DTO.CitizenResponseDTO;
import com.example.eshop.model.Cart;
import com.example.eshop.model.Citizen;

import java.util.List;

public class CartMapper {
    public static CartResponseDTO toDTO(Cart cart) {
        List<CartItemResponseDTO> items = cart.getItems().stream()
                .map(ci -> new CartItemResponseDTO(
                        ci.getItem().getItemId(),
                        ci.getItem().getName(),
                        ci.getItem().getPrice(),
                        ci.getQuantity()
                ))
                .toList();

        return new CartResponseDTO(
                cart.getCartID(),
                cart.getCitizen().getAfm(),
                cart.getCartPrice(),
                items
        );
    }
}
