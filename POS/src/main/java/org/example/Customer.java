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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String name;
    private String address;
    private String nic;
    private String email;
    private int tel;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Orders> orders;

    public Customer(String name, String address, String email, String nic, int tel, List<Orders> orders) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.nic = nic;
        this.tel = tel;
        this.orders = orders;
    }
}
