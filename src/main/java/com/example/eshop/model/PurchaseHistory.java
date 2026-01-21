package com.example.eshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase_history")
public class PurchaseHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int citizenAfm;
    private int storeAfm;
    private String productName;
    private int quantity;
    private double totalPrice;
    private LocalDateTime date;
}
