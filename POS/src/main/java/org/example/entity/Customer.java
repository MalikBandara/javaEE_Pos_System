package org.example.entity;

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
    @Column(name = "Customer_Name", nullable = false)
    private String CustomerName;
    @Column(name = "Customer_Address", nullable = false)
    private String CustomerAddress;
    @Column(name = "Customer_Salary", nullable = false)
    private String CustomerSalary;
    @Column(name = "Customer_Mobile", nullable = false)
    private String CustomerMobile;
    @Column(name = "Customer_Email", nullable = false)
    private String CustomerEmail;


    public Customer(String name, String address, String salary, String mobile, String email) {
         this.CustomerName = name;
         this.CustomerAddress= address;
         this.CustomerSalary = salary;
         this.CustomerMobile = mobile;
         this.CustomerEmail= email;
    }
}
