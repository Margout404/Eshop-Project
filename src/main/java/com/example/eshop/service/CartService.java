package com.example.eshop.service;

import com.example.eshop.DTO.cartDTO.AddToCartDTO;
import com.example.eshop.DTO.cartDTO.CartMapper;
import com.example.eshop.DTO.cartDTO.CartResponseDTO;
import com.example.eshop.model.Cart;
import com.example.eshop.model.CartItem;
import com.example.eshop.model.Citizen;
import com.example.eshop.model.Item;
import com.example.eshop.repository.CartItemRepository;
import com.example.eshop.repository.CartRepository;
import com.example.eshop.repository.CitizenRepository;
import com.example.eshop.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CitizenRepository citizenRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Transactional
    public CartResponseDTO addItemToCart(AddToCartDTO dto){
        Citizen citizen= citizenRepository.findById(dto.citizenAfm())
                .orElseThrow(()-> new RuntimeException("Citizen not found"));

        Cart cart = cartRepository.findByCitizenAfm(dto.citizenAfm())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCitizen(citizen);
                    newCart.setCartPrice(0.0);
                    return cartRepository.save(newCart);
                });

        Item item= itemRepository.findById(dto.itemId())
                .orElseThrow(()-> new RuntimeException("Item not found"));

        if (item.getQuantity() < dto.quantity()){
            throw new IllegalArgumentException("Not enough stock");
        }

//        check if item already exists
        CartItem cartItem= cart.getItems().stream()
                .filter(it->it.getItem().getItemId().equals(item.getItemId()))
                .findFirst()
                .orElse(null);

        if (cartItem == null ){
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setItem(item);
            cartItem.setQuantity(dto.quantity());
            cart.getItems().add(cartItem);
        } else {
            cartItem.setQuantity(dto.quantity()+ cartItem.getQuantity());
        }
//        calculate total price for cart
        double total=0;
        for (CartItem itemInCart:cart.getItems()){
            total+= itemInCart.getQuantity()*itemInCart.getItem().getPrice();
        }
        cart.setCartPrice(total);
        return CartMapper.toDTO(cart);


    }

    public CartResponseDTO getCartForCitizen(int citizenAfm) {
        Citizen citizen = citizenRepository.findById(citizenAfm).orElseThrow(()-> new RuntimeException("Citizen not found"));
        Cart cart = citizen.getCart();

        if (cart == null) {
            cart = new Cart();
            cart.setCitizen(citizen);
            cart.setCartPrice(0.0);
            citizen.setCart(cart);
        }

        return CartMapper.toDTO(cart);
    }

    @Transactional
    public void checkout(int citizenAfm){
        Cart cart = cartRepository.findByCitizenAfm(citizenAfm).orElseThrow(()-> new RuntimeException("Cart not found"));

        for(CartItem it: cart.getItems()){
            Item item = it.getItem();
            if(item.getQuantity()< it.getQuantity()){
                throw new IllegalArgumentException("Not enough stock for item : "+ item.getName());
            }
        }
        for(CartItem it : cart.getItems()){
            Item item = it.getItem();
            item.setQuantity(item.getQuantity()-it.getQuantity());
            itemRepository.save(item);
        }
        cart.getItems().clear();
        cart.setCartPrice(0.0);
        cartRepository.save(cart);
    }
}
