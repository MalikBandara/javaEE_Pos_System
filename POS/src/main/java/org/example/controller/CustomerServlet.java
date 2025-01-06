package org.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bo.BO.CustomerBo;
import org.example.bo.BoFactory;
import org.example.bo.BoTypes;
import org.example.db.SessionFactoryConfuguration;
import org.example.dto.CustomerDTO;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/customer")

public class CustomerServlet extends HttpServlet {




    CustomerBo customerBo = (CustomerBo) BoFactory.getBoFactory().getBo(BoTypes.CUSTOMER);


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("customerId");

        int id = Integer.parseInt(customerId);

        boolean b = customerBo.deleteCustomer(id);

        if (b) {
            resp.setStatus(200);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Customer Delete successfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);

            resp.getWriter().write("Customer not found");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //ena data DTO type eke data
        List<CustomerDTO> allCustomer = customerBo.getAllCustomer();

        System.out.println("Fetched customers: " + allCustomer); // Debug line


        // list ekak hadagena ekata dagannava

        List<Customer> customerList = new ArrayList<>();


        //DTO dat customer Entity ekt da gannava
        for (CustomerDTO customerDTO : allCustomer) {
            Customer customer = new Customer();
            customer.setCustomerId(customerDTO.getCustomerId());
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setCustomerEmail(customerDTO.getCustomerEmail());
            customer.setCustomerMobile(customerDTO.getCustomerMobile());
            customer.setCustomerSalary(customerDTO.getCustomerSalary());
            customer.setCustomerAddress(customerDTO.getCustomerAddress());


            //list ekt DTO eken entity ekt dagatta data tika add krnv
            customerList.add(customer);
        }

        //jason array

        JsonArrayBuilder allcustomers = Json.createArrayBuilder();


        //list ek loop karala jason object ekata data dagannava
        for (Customer customer : customerList){
            JsonObjectBuilder customerObject = Json.createObjectBuilder();

            customerObject.add("customerId",customer.getCustomerId());
            customerObject.add("Customer_Name",customer.getCustomerName());
            customerObject.add("Customer_Address",customer.getCustomerAddress());
            customerObject.add("Customer_Salary",customer.getCustomerSalary());
            customerObject.add("Customer_Mobile",customer.getCustomerMobile());
            customerObject.add("Customer_Email",customer.getCustomerEmail());


            //jason array ekt jason object data tika dagannava
            allcustomers.add(customerObject);
        }


        //yavana type ek application/json kiyl dagannava


        //get method to CustomerController.js passing data and set it in to table in js file
        resp.setContentType("application/json");
        resp.getWriter().write(allcustomers.build().toString());


    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            String customerId = req.getParameter("customerId");
            String name = req.getParameter("Customer_Name");
            String address = req.getParameter("Customer_Address");
            String mobile = req.getParameter("Customer_Mobile");
            String email = req.getParameter("Customer_Email");
            String salaryInput = req.getParameter("Customer_Salary");

            int id = Integer.parseInt(customerId);

            CustomerDTO customerDTO = new CustomerDTO(id, name, address, salaryInput, mobile, email);

            boolean b = customerBo.updateCustomer(customerDTO);

            if (b) {
                resp.setStatus(200);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Customer Update successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Customer not found");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {


            String name = req.getParameter("Customer_Name");
            String address = req.getParameter("Customer_Address");
            String mobile = req.getParameter("Customer_Mobile");
            String email = req.getParameter("Customer_Email");
            String salaryInput = req.getParameter("Customer_Salary");


            CustomerDTO customerDTO = new CustomerDTO(name, address, salaryInput, mobile, email);

            boolean b = customerBo.SaveCustomer(customerDTO);

            if (b) {
                resp.setStatus(200);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Customer save successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Customer not found");
            }
        }catch (Exception e ){
            throw new RuntimeException(e);
        }

    }


}
