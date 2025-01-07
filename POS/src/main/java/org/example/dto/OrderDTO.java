package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Customer;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private String orderId;
    private String itemName;
    private String itemPrice;
    private String Quantity;
    private String subtotal;
    private Customer customer;
}
