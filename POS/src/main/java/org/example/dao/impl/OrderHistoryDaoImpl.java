package org.example.dao.impl;

import org.example.dao.dao.OrderHistoryDao;
import org.example.db.SessionFactoryConfuguration;
import org.example.entity.OrderHistory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDaoImpl implements OrderHistoryDao {
    @Override
    public boolean save(OrderHistory orderHistory) {
        try {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(orderHistory);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OrderHistory orderHistory) {
        return false;
    }

    @Override
    public List<OrderHistory> getAll() {

        try {

            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction transaction = session.beginTransaction();

            List<OrderHistory> orderHistories = new ArrayList<>();
            orderHistories =  session.createQuery("from OrderHistory ").list();
            transaction.commit();
            session.close();
            return orderHistories;

        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }
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
