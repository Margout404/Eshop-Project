package com.example.eshop.controller;

import com.example.eshop.model.PurchaseHistory;
import com.example.eshop.repository.PurchaseHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    PurchaseHistoryRepository historyRepository;

    @GetMapping("/citizen/{afm}")
    public List<PurchaseHistory> getCitizenHistory(@PathVariable int afm) {
        return historyRepository.findByCitizenAfm(afm);
    }

    @GetMapping("/store/{afm}")
    public List<PurchaseHistory> getStoreHistory(@PathVariable int afm) {
        return historyRepository.findByStoreAfm(afm);
    }
}