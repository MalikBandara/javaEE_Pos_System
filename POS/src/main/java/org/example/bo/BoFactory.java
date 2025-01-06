package org.example.bo;

import org.example.bo.impl.CustomerBoImpl;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getBoFactory(){
    if (boFactory == null){
        boFactory = new BoFactory();

    }
    return boFactory;

    }


    public SuperBO getBo (BoTypes boTypes){
    switch (boTypes){
        case CUSTOMER :
            return new CustomerBoImpl();
        default:
            return null;
    }

    }
}
