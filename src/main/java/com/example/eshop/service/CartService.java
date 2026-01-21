package com.example.eshop.service;

import com.example.eshop.DTO.cartDTO.AddToCartDTO;
import com.example.eshop.DTO.cartDTO.CartMapper;
import com.example.eshop.DTO.cartDTO.CartResponseDTO;
import com.example.eshop.model.*;
import com.example.eshop.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    PurchaseHistoryRepository historyRepository;

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
    public void checkout(int citizenAfm) {
        Cart cart = cartRepository.findByCitizenAfm(citizenAfm)
                .orElseThrow(() -> new RuntimeException("Δεν βρέθηκε καλάθι για αυτον τον χρήστη με ΑΦΜ : " + citizenAfm));

        for (CartItem it : cart.getItems()) {
            if (it.getItem().getQuantity() < it.getQuantity()) {
                throw new IllegalArgumentException("Δεν υπάρχει αρκετό απόθεμα για: " + it.getItem().getName());
            }
        }

        for (CartItem it : cart.getItems()) {
            Item item = it.getItem();

            item.setQuantity(item.getQuantity() - it.getQuantity());
            itemRepository.save(item);

            PurchaseHistory history = new PurchaseHistory();
            history.setCitizenAfm(citizenAfm);
            history.setStoreAfm(item.getStore().getAfm());
            history.setProductName(item.getName());
            history.setQuantity(it.getQuantity());
            history.setTotalPrice(it.getQuantity() * item.getPrice());
            history.setDate(java.time.LocalDateTime.now());

            historyRepository.save(history);
        }

        cart.getItems().clear();
        cart.setCartPrice(0.0);
        cartRepository.save(cart);
    }

    @Transactional
    public void removeItemFromCart(int citizenAfm, Long itemId) {
        Cart cart = cartRepository.findByCitizenAfm(citizenAfm)
                .orElseThrow(() -> new RuntimeException("Δεν βρέθηκε καλάθι"));

        boolean removed = cart.getItems().removeIf(ci -> ci.getItem().getItemId().equals(itemId));

        if (removed) {
            double total = 0;
            for (CartItem itemInCart : cart.getItems()) {
                total += itemInCart.getQuantity() * itemInCart.getItem().getPrice();
            }
            cart.setCartPrice(total);

            cartRepository.save(cart);
        }
    }
}
