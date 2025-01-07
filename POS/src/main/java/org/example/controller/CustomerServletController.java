package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bo.BO.CustomerBo;
import org.example.bo.BoFactory;
import org.example.bo.BoTypes;
import org.example.dto.CustomerDTO;
import org.example.entity.Customer;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/customer/*")

public class CustomerServletController extends HttpServlet {




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
        try {
            String pathInfo = req.getPathInfo();
            System.out.println("Customer Path info: " + pathInfo);

            if (pathInfo != null && pathInfo.equals("/nextCus-id")) {
                // Fetch next customer ID
                int nextCustomerId = customerBo.getNextCustomerId();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("{\"nextIdCus\": " + nextCustomerId + "}");
            } else if (pathInfo == null || pathInfo.equals("/")) {
                // Fetch all customers
                List<CustomerDTO> allCustomer = customerBo.getAllCustomer();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                if (allCustomer != null && !allCustomer.isEmpty()) {
                    JsonArrayBuilder allCustomersJson = Json.createArrayBuilder();
                    for (CustomerDTO customerDTO : allCustomer) {
                        JsonObjectBuilder customerJson = Json.createObjectBuilder()
                                .add("customerId", customerDTO.getCustomerId())
                                .add("Customer_Name", customerDTO.getCustomerName())
                                .add("Customer_Email", customerDTO.getCustomerEmail())
                                .add("Customer_Mobile", customerDTO.getCustomerMobile())
                                .add("Customer_Salary", customerDTO.getCustomerSalary())
                                .add("Customer_Address", customerDTO.getCustomerAddress());
                        allCustomersJson.add(customerJson);
                    }
                    resp.getWriter().write(allCustomersJson.build().toString());
                } else {
                    resp.getWriter().write("{\"error\": \"No customers found\"}");
                }
            } else {
                // Invalid endpoint
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Invalid endpoint\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Failed to process request: " + e.getMessage() + "\"}");
        }
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

            String customerId = req.getParameter("customerId");
            String name = req.getParameter("Customer_Name");
            String address = req.getParameter("Customer_Address");
            String mobile = req.getParameter("Customer_Mobile");
            String email = req.getParameter("Customer_Email");
            String salaryInput = req.getParameter("Customer_Salary");

            int id = Integer.parseInt(customerId);


            CustomerDTO customerDTO = new CustomerDTO(id,name, address, salaryInput, mobile, email);

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
