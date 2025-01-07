package org.example.dao.impl;

import org.example.dao.CrudDAO;
import org.example.dao.dao.CustomerDao;
import org.example.db.SessionFactoryConfuguration;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    public boolean save(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Only rollback if transaction exists
            }
            throw new RuntimeException("Error saving customer", e);
        } finally {
            if (session != null) {
                session.close();  // Ensure session is closed after completion
            }
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

        try {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, customerId);

            session.delete(customer);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e ){
             e.printStackTrace();
             return false;
        }



    }

    @Override
    public int IdGenerate() {
        try (Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession()){
            String hql = "SELECT COALESCE(MAX(c.customerId), 0) + 1 FROM Customer c";
            Query<Integer> query = session.createQuery(hql, Integer.class);
            return query.uniqueResult();
        }catch (Exception e ){
            e.printStackTrace();
            return 1;
        }
    }
}
