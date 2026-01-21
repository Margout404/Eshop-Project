package com.example.eshop.controller;


import com.example.eshop.DTO.ItemResponseDTO;
import com.example.eshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemSearchController {

    @Autowired
    ItemService itemService;


    @GetMapping("/search")
    public List<ItemResponseDTO> getItemsByName(@RequestParam String name){
        return itemService.searchByName(name);
    }

    @GetMapping("/searchByPrice")
    public List<ItemResponseDTO> getItemsByPrice( @RequestParam double min,@RequestParam double max){
        return itemService.searchByPrice(min,max);
    }
}
