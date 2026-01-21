package com.example.eshop.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "citizen")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Citizen {

    @Id
    @EqualsAndHashCode.Include
    private int afm;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne(mappedBy = "citizen",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Cart cart;


}
