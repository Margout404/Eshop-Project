package com.example.eshop.controller;

import com.example.eshop.DTO.StoreResponseDTO;
import com.example.eshop.model.Store;
import com.example.eshop.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    StoreService storeService;

    @GetMapping("/allStores")
    public List<StoreResponseDTO> getAllStores(){
        return storeService.getAllStores();
    }
}
