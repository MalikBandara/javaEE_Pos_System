package org.example.bo.BO;

import org.example.bo.SuperBO;
import org.example.dto.OrderHistoryDTO;

import java.util.List;

public interface OrderHistoryBo extends SuperBO {
    boolean saveOrderHistory(OrderHistoryDTO orderHistoryDTO);

    List<OrderHistoryDTO> getAllOrderHistory();
}
