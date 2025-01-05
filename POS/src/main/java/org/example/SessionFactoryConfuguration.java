package org.example;



import jakarta.persistence.criteria.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfuguration {

    private static SessionFactoryConfuguration sessionFactoryConfuguration;

    private static SessionFactory sessionFactory;

    public SessionFactoryConfuguration() {
        Configuration configure = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Item.class).addAnnotatedClass(ItemDetails.class).addAnnotatedClass(Orders.class);
        sessionFactory = configure.buildSessionFactory();
    }

    public static SessionFactoryConfuguration getSessionFactoryConfuguration(){
        if(sessionFactoryConfuguration == null){
            sessionFactoryConfuguration = new SessionFactoryConfuguration();
        }
        return sessionFactoryConfuguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }

}
