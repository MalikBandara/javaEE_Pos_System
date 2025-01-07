package org.example.dao.impl;

import org.example.dao.dao.OrderDao;
import org.example.entity.Order;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Order order) {
        return false;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public boolean delete(int customerId) {
        return false;
    }

    @Override
    public int IdGenerate() {
        return 0;
    }
}
