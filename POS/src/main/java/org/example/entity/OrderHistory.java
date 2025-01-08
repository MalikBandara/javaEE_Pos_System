package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderHistory {

    @Id
    private int orderId;

    private Date date;

    private String itemPrice;  // This can be changed to double or BigDecimal for calculations
    private int orderQuantity;
    private String finalTotal;  // Can be changed to double for numeric calculations

    @ManyToOne
    @JoinColumn(name = "customer_id")  // Foreign key
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "item_code")  // Foreign key
    private Item item;



}
