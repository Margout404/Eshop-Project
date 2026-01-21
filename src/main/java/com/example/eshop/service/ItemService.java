package com.example.eshop.service;

import com.example.eshop.DTO.ItemCreateDTO;
import com.example.eshop.DTO.ItemResponseDTO;
import com.example.eshop.DTO.StoreMapper;
import com.example.eshop.model.Item;
import com.example.eshop.model.Store;
import com.example.eshop.repository.ItemRepository;
import com.example.eshop.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StoreRepository storeRepository;

    public List<ItemResponseDTO> getAllItemsForStore(int afm){
        Store store= storeRepository.findById(afm).orElseThrow();
        return itemRepository.findByStore(store).stream().map(StoreMapper::toDTO).toList();
    }

    public ItemResponseDTO addItemToStore(int afm, ItemCreateDTO dto){
        Store store= storeRepository.findById(afm).orElseThrow(() -> new IllegalArgumentException("Store not found"));
        Item item = new Item();
        item.setName(dto.name());
        item.setBrand(dto.brand());
        item.setDescription(dto.description());
        item.setPrice(dto.price());
        item.setQuantity(dto.quantity());
        item.setStore(store);
        Item saved= itemRepository.save(item);
        return StoreMapper.toDTO(saved);
    }

    public ItemResponseDTO updateItemQuantity(Long itemId, int newQuantity) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Το αντικείμενο δεν βρέθηκε"));

        item.setQuantity(item.getQuantity() + newQuantity);
        Item updatedItem = itemRepository.save(item);

        return StoreMapper.toDTO(updatedItem);
    }

    public List<ItemResponseDTO> searchByName(String name){
        return itemRepository.findByNameContainingIgnoreCase(name).stream().map(StoreMapper::toDTO).toList();
    }

    public List<ItemResponseDTO> searchByPrice(double min, double max){
        return itemRepository.findByPriceBetween(min,max).stream().map(StoreMapper::toDTO).toList();
    }
}
