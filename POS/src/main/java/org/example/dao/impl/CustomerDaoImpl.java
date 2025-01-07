package org.example.dao.impl;

import org.example.dao.CrudDAO;
import org.example.dao.dao.CustomerDao;
import org.example.db.SessionFactoryConfuguration;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer customer) {

        Transaction transaction = null;
        //session auto matic close because input into try block
        try(Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();

            return true;
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(Customer customer) {

        Transaction transaction = null;
        //session auto matic close because input into try block
        try(Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession()) {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();

            return true;
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Customer> getAll() {

        Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
        Transaction transaction = session.beginTransaction();

        List<Customer> customers = new ArrayList<>();

        customers = session.createQuery("from Customer").list();

        transaction.commit();
        session.close();

        return customers;

    }

    @Override
    public boolean delete(int customerId) {

        Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, customerId);

        session.delete(customer);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public int IdGenerate() {
        return 0;
    }
}
