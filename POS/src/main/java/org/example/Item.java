package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String model;
    private double price;
    private int quantity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item",cascade = CascadeType.ALL)
    private List<ItemDetails> itemDetails;

    public Item(String model, double price, int quantity, List<ItemDetails> itemDetails) {
        this.model = model;
        this.price = price;
        this.quantity = quantity;
        this.itemDetails = itemDetails;
    }
}
