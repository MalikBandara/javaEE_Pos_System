package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.db.SessionFactoryConfuguration;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(urlPatterns = "/customer")

public class CustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Session session = SessionFactoryConfuguration.getSessionFactoryConfuguration().getSession();
            Transaction transaction = session.beginTransaction();

            String name = req.getParameter("Customer_Name");
            String address = req.getParameter("Customer_Address");
            String mobile = req.getParameter("Customer_Mobile");
            String email = req.getParameter("Customer_Email");
            String salaryInput = req.getParameter("Customer_Salary");


            Customer customer = new Customer(name, address, salaryInput, mobile, email);
            session.save(customer);
            transaction.commit();
            session.close();


        }catch (Exception e ){
                e.printStackTrace();


        }






    }


}
