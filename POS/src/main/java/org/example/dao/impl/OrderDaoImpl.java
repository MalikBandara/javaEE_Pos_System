package org.example.dao.impl;

import org.example.dao.dao.OrderDao;
import org.example.db.SessionFactoryConfuguration;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Order order) {
        Transaction transaction = null;
        try (Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession()){
             transaction=  session.beginTransaction();
             session.save(order);
             transaction.commit();
             return true;
        }catch (Exception e ){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public List<Order> getAll() {

        Transaction transaction = null ;
        try(Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession()) {
           transaction =  session.beginTransaction();

           List<Order> orders = new ArrayList<>();
           orders = session.createQuery("from Order").list();
           transaction.commit();
           return orders;


        }catch (Exception e ){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public boolean delete(String customerId) {
        return false;
    }

    @Override
    public boolean delete(int orderid) {
        Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
        Transaction transaction = session.beginTransaction();

        // Fetch the order by its ID
        Order order = session.get(Order.class, orderid);

        // Check if the order is found
        if (order != null) {
            // If the order exists, delete it
            session.delete(order);
            transaction.commit();
        } else {
            // Handle the case where the order does not exist
            System.out.println("Order not found with ID: " + orderid);
            transaction.rollback(); // Rollback the transaction if the order does not exist
            return false; // Indicate failure to delete
        }

        session.close();
        return true; // Indicate successful deletion
    }

    @Override
    public int IdGenerate() {
        return 0;
    }

    @Override
    public Customer getAllCustomers(String customerId) {


        try {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Customer WHERE customerId = :customerId";
            Query<Customer> query = session.createQuery(hql, Customer.class);
            query.setParameter("customerId" , customerId);

            Customer customer = query.uniqueResult();

            transaction.commit();
            return customer;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Item getAllItems(int itemCode) {
        try {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Item WHERE ItemCode = : ItemCode";
            Query<Item> query = session.createQuery(hql, Item.class);
            query.setParameter("ItemCode",itemCode);

            Item item = query.uniqueResult();

            return item;

        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }
    }
}
