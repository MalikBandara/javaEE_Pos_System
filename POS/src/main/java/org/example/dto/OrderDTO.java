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
    private int orderId;
    private int Quantity;
    private int ItemCode;
    private String itemName;
    private String itemPrice;
    private String subtotal;
    private Customer customer;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", quantity='" + Quantity + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", customer=" + customer +
                '}';
    }
}
