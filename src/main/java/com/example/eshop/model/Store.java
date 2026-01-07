package com.example.eshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
@Entity
public class Store {
    @Id
    private int afm;

    private String storeName;
    private String owner;
    private String password;

    @OneToMany(mappedBy="store", fetch = FetchType.LAZY)
    private List<Item> items;
    public int getAfm() {
        return afm;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getOwner() {
        return owner;
    }

    public String getPassword() {
        return password;
    }

    public List<Item> getItems() {
        return items;
    }
}
