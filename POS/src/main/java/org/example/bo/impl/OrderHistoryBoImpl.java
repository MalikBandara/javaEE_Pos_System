package org.example.bo.impl;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.bo.BO.OrderHistoryBo;
import org.example.dao.DaoFactory;
import org.example.dao.DaoTypes;
import org.example.dao.dao.OrderHistoryDao;
import org.example.dto.OrderHistoryDTO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.OrderHistory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHistoryBoImpl implements OrderHistoryBo {

    OrderHistoryDao orderHistoryDao = (OrderHistoryDao) DaoFactory.getDaoFactory().getDao(DaoTypes.ORDERHISTORY);
    @Override
    public boolean saveOrderHistory(OrderHistoryDTO orderHistoryDTO) {

       return orderHistoryDao.save(new OrderHistory(orderHistoryDTO.getOrderId(),orderHistoryDTO.getDate(),orderHistoryDTO.getItemPrice(),orderHistoryDTO.getOrderQuantity(),orderHistoryDTO.getFinalTotal(),orderHistoryDTO.getCustomer(),orderHistoryDTO.getItem()));

    }

    @Override
    public List<OrderHistoryDTO> getAllOrderHistory() {
        List<OrderHistory> all = orderHistoryDao.getAll();

        List<OrderHistoryDTO> orderHistoryDTOS = new ArrayList<>();

        for (OrderHistory orderHistory : all){
            OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO(orderHistory.getOrderId(),orderHistory.getDate(),orderHistory.getItemPrice(),orderHistory.getOrderQuantity(),orderHistory.getFinalTotal(),orderHistory.getCustomer(),orderHistory.getItem());
            orderHistoryDTOS.add(orderHistoryDTO);

        }
        return orderHistoryDTOS;

    }
}
