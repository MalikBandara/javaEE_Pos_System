package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Customer;
import org.example.entity.Item;

import java.util.Date;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderHistoryDTO {

    private int orderId;
    private Date date;
    private String itemPrice;  // This can be changed to double or BigDecimal for calculations
    private int orderQuantity;
    private String finalTotal;  // Can be changed to double for numeric calculations
    private Customer customer;
    private Item item;
}
