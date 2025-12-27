package com.example.eshop.controller;

import com.example.eshop.DTO.ItemCreateDTO;
import com.example.eshop.DTO.ItemResponseDTO;
import com.example.eshop.model.Item;
import com.example.eshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores/{storeAfm}/items")
public class ItemsController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public List<ItemResponseDTO> getAllItemsForStore(@PathVariable("storeAfm") int afm){
        return itemService.getAllItemsForStore(afm);
    }

    @PostMapping
    public ItemResponseDTO addItem(@PathVariable("storeAfm") int afm, @RequestBody ItemCreateDTO item){
        return itemService.addItemToStore(afm, item);
    }

    @PutMapping("/{itemID}")
    public ItemResponseDTO updateQuantity(@PathVariable Long itemID, @RequestBody int quantity){
        return itemService.updateItemQuantity(itemID,quantity);
    }


}
