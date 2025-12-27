package com.example.eshop.service;

import com.example.eshop.DTO.StoreMapper;
import com.example.eshop.DTO.StoreResponseDTO;
import com.example.eshop.model.Store;
import com.example.eshop.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public List<StoreResponseDTO> getAllStores(){
        return storeRepository.findAllWithItems().stream().map(StoreMapper::toDTO).toList();
    }
}
