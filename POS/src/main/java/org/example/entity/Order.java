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
    private String orderId;
    private String itemName;
    private String itemPrice;
    private String Quantity;
    private String subtotal;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Foreign key column
    private Customer customer;
}
