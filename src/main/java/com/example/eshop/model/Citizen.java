package com.example.eshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "citizen")
@Entity
public class Citizen {

    @Id
    @GeneratedValue
    private int afm;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartID",referencedColumnName = "cartID")
    private Cart cart;
}
