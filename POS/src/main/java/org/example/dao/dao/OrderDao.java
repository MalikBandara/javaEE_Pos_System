package org.example.dao.dao;

import org.example.dao.CrudDAO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.Order;

import java.util.List;

public interface OrderDao extends CrudDAO<Order> {
    Customer getAllCustomers(String customerId);

    Item getAllItems(String itemCode);
}
