package org.example.dao.impl;

import org.example.dao.dao.OrderHistoryDao;
import org.example.entity.OrderHistory;

import java.util.List;

public class OrderHistoryDaoImpl implements OrderHistoryDao {
    @Override
    public boolean save(OrderHistory orderHistory) {
        return false;
    }

    @Override
    public boolean update(OrderHistory orderHistory) {
        return false;
    }

    @Override
    public List<OrderHistory> getAll() {
        return null;
    }

    @Override
    public boolean delete(int customerId) {
        return false;
    }

    @Override
    public boolean delete(String customerId) {
        return false;
    }

    @Override
    public int IdGenerate() {
        return 0;
    }
}
