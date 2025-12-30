package com.example.eshop.controller;

import com.example.eshop.DTO.cartDTO.AddToCartDTO;
import com.example.eshop.DTO.cartDTO.CartResponseDTO;
import com.example.eshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartContoller {

    @Autowired
    CartService cartService;

    @PostMapping("/items")
    public CartResponseDTO addItem(@RequestBody AddToCartDTO dto) {
        return cartService.addItemToCart(dto);
    }

    @GetMapping("/{citizenAfm}")
    public CartResponseDTO getCart(@PathVariable int citizenAfm) {
        return cartService.getCartForCitizen(citizenAfm);
    }
    @PostMapping("/checkout/{citizenAfm}")
    public String checkout(@PathVariable int citizenAfm) {
        cartService.checkout(citizenAfm);
        return "Purchase completed successfully";
    }


}
