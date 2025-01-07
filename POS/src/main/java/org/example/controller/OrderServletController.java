package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bo.BO.OrderBo;
import org.example.bo.BoFactory;
import org.example.bo.BoTypes;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.OrderDTO;
import org.example.entity.Customer;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/order/*")
public class OrderServletController extends HttpServlet {


    OrderBo orderBo = (OrderBo) BoFactory.getBoFactory().getBo(BoTypes.ORDER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        String quantity = req.getParameter("Quantity");
        String itemName = req.getParameter("itemName");
        String itemPrice = req.getParameter("itemPrice");
        String subtotal = req.getParameter("subtotal");
        String customerId = req.getParameter("customer_id");

        CustomerDTO customerDTO = orderBo.searchByCustomerId(customerId);
        Customer customer = new Customer(customerDTO.getCustomerId(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(), customerDTO.getCustomerSalary(), customerDTO.getCustomerMobile(), customerDTO.getCustomerEmail());

        OrderDTO orderDTO = new OrderDTO(orderId, quantity, itemName, itemPrice, subtotal, customer);

        boolean b = orderBo.saveOrders(orderDTO);

        if (b){
            resp.setStatus(200);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Order Save successfully");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Item not found");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {


            String pathInfo = req.getPathInfo();

            if (pathInfo != null && pathInfo.equals("/customer")) {
                String customerId = req.getParameter("customerId");
                CustomerDTO customerDTO = orderBo.searchByCustomerId(customerId);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                JsonArrayBuilder CustomerArray = Json.createArrayBuilder();

                JsonObjectBuilder CustomerObj = Json.createObjectBuilder().
                        add("customerId", customerDTO.getCustomerId()).
                        add("Customer_Name", customerDTO.getCustomerName()).
                        add("Customer_Address", customerDTO.getCustomerAddress()).
                        add("Customer_Mobile", customerDTO.getCustomerMobile()).
                        add("Customer_Email", customerDTO.getCustomerEmail()).
                        add("Customer_Salary", customerDTO.getCustomerSalary());

                CustomerArray.add(CustomerObj);
                resp.getWriter().write(CustomerArray.build().toString());

            }else if (pathInfo != null && pathInfo.equals("/item")){
                String itemCode = req.getParameter("ItemCode");

                ItemDTO itemDTO = orderBo.searchByItemId(itemCode);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                JsonArrayBuilder ItemArray = Json.createArrayBuilder();

                JsonObjectBuilder itemObject = Json.createObjectBuilder().
                        add("ItemCode",itemDTO.getItemCode()).
                        add("ItemName",itemDTO.getItemName()).
                        add("ItemPrice",itemDTO.getItemPrice()).
                        add("Quantity",itemDTO.getQuantity());

                ItemArray.add(itemObject);
                resp.getWriter().write(ItemArray.build().toString());
            }else if (pathInfo != null && pathInfo.equals("/cart")){

                List<OrderDTO> allOrders = orderBo.getAllOrders();

                JsonArrayBuilder AllOrderArr = Json.createArrayBuilder();


                for (OrderDTO orderDTO : allOrders) {
                    JsonObjectBuilder AllOrderObj = Json.createObjectBuilder().
                            add("orderId" , orderDTO.getOrderId()).
                            add("Quantity",orderDTO.getQuantity()).
                            add("itemName",orderDTO.getItemName()).
                            add("itemPrice",orderDTO.getItemPrice()).
                            add("subtotal",orderDTO.getSubtotal()).
                            add("customer_id", 1);

                    AllOrderArr.add(AllOrderObj);
                }
                resp.setContentType("application/json");
                resp.getWriter().write(AllOrderArr.build().toString());
            }

        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"error\": \"Failed to process request\"}");
        }
    }
}
