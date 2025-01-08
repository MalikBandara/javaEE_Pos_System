package org.example.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    private int orderId;
    private int itcode;
    private String itemName;
    private String itemPrice;
    private int Quantity;
    private String subtotal;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Foreign key column
    private Customer customer;
}
