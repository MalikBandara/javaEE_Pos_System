package org.example.bo.BO;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.OrderDTO;

import java.util.List;

public interface OrderBo extends SuperBO {
    CustomerDTO searchByCustomerId(String customerId);

    ItemDTO searchByItemId(int itemCode);

    boolean saveOrders(OrderDTO orderDTO);

    List<OrderDTO> getAllOrders();

    boolean deleteCartOrder(int orderid);

    boolean deleteCartOrder(String  orderid);
}
