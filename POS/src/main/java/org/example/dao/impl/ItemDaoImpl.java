package org.example.dao.impl;

import org.example.dao.dao.ItemDao;
import org.example.db.SessionFactoryConfuguration;
import org.example.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item item) {

        Transaction transaction = null;
        try {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Item item) {

        Transaction transaction = null;

        try (Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession()){
            transaction =  session.beginTransaction();
            session.update(item);
            transaction.commit();
            return true;

        }catch (Exception e ){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<Item> getAll() {

        Transaction transaction = null;
        try (Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession()){
             transaction =  session.beginTransaction();


             List<Item> itemList = new ArrayList<>();
             itemList = session.createQuery("from Item").list();
             transaction.commit();
             return itemList;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public boolean delete(int ItemId) {
        Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
        Transaction transaction = session.beginTransaction();

        Item item = session.get(Item.class , ItemId);
        session.delete(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String customerId) {
        return false;
    }

    @Override
    public int IdGenerate() {

        try (Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession()){
            String hql = "SELECT COALESCE(MAX(i.ItemCode), 0) + 1 FROM Item i";
            Query<Integer> query = session.createQuery(hql, Integer.class);
            return query.uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
            return 1; // Start with 1 if the query fails
        }


    }
}
