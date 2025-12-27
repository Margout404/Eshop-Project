package com.example.eshop.repository;

import com.example.eshop.model.CartItem;
import com.example.eshop.model.Item;
import com.example.eshop.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByStore(Store store);
}
