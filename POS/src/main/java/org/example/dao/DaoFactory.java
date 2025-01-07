package org.example.dao;

import org.example.dao.impl.CustomerDaoImpl;
import org.example.dao.impl.ItemDaoImpl;

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

            default:
                return null;
        }
    }
}
