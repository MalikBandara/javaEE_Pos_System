package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_ORDERS"))
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "FK_ITEM"))
    private Item item;

    public ItemDetails(int quantity, Orders orders, Item item) {
        this.quantity = quantity;
        this.orders = orders;
        this.item = item;
    }
}
