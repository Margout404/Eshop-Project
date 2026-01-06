package com.example.eshop.repository;

import com.example.eshop.model.CartItem;
import com.example.eshop.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Integer> {

    @Query("""
    SELECT DISTINCT s
    FROM Store s
    LEFT JOIN FETCH s.items
""")
    List<Store> findAllWithItems();

    Optional<Store> findByAfm(int afm);
}
