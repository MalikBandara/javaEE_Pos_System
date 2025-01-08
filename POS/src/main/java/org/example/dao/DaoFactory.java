package org.example.dao;

import org.example.dao.impl.CustomerDaoImpl;
import org.example.dao.impl.ItemDaoImpl;
import org.example.dao.impl.OrderDaoImpl;
import org.example.dao.impl.OrderHistoryDaoImpl;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getDaoFactory(){
        if (daoFactory == null){
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }


    public SuperDAO getDao(DaoTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER :
                return new CustomerDaoImpl();

            case ITEM:
                return new ItemDaoImpl();

            case ORDER:
                return new OrderDaoImpl();
            case ORDERHISTORY:
                return new OrderHistoryDaoImpl();

            default:
                return null;
        }
    }
}
