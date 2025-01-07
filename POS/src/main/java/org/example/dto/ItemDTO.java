package org.example.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {

    private int ItemCode;

    private String ItemName;

    private String ItemPrice;

    private String Quantity;

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemCode=" + ItemCode +
                ", itemName='" + ItemName + '\'' +
                ", itemPrice=" + ItemPrice +
                ", quantity=" + Quantity +
                '}';
    }
}
