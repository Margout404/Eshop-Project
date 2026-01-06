package com.example.eshop.service;

import com.example.eshop.DTO.StoreCreateDTO;
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

    public StoreResponseDTO createStore(StoreCreateDTO dto){
        if(storeRepository.findByAfm(dto.afm()).isPresent()){
            throw new IllegalArgumentException("Store with this Afm already exists");
        }
        Store store = new Store();
        store.setAfm(dto.afm());
        store.setStoreName(dto.storeName());
        store.setOwner(dto.owner());
        store.setPassword(dto.password());
        storeRepository.save(store);
        return StoreMapper.toDTO(store);
    }
}
