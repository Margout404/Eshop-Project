package com.example.eshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long cartID;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<CartItem> items= new HashSet<>();

    @OneToOne
    @JoinColumn(name = "citizen_afm", nullable = false, unique = true)
    @ToString.Exclude
    private Citizen citizen;

    private double cartPrice;
}
