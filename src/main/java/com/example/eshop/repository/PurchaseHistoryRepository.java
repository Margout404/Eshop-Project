package com.example.eshop.repository;

import com.example.eshop.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    List<PurchaseHistory> findByCitizenAfm(int citizenAfm); // Ιστορικό Πελάτη
    List<PurchaseHistory> findByStoreAfm(int storeAfm);     // Ιστορικό Μαγαζιού
}
