package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private Date date;
    private double total;

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_CUSTOMER"))
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orders",cascade = CascadeType.ALL)
    private List<ItemDetails> itemDetails;

    public Orders(Date date, double total, Customer customer, List<ItemDetails> itemDetails) {
        this.date = date;
        this.total = total;
        this.customer = customer;
        this.itemDetails = itemDetails;
    }

    public Orders(Date date, double total, Customer customer) {
        this.date = date;
        this.total = total;
        this.customer = customer;
    }
}
