package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bo.BO.OrderBo;
import org.example.bo.BO.OrderHistoryBo;
import org.example.bo.BoFactory;
import org.example.bo.BoTypes;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.OrderHistoryDTO;
import org.example.entity.Customer;
import org.example.entity.Item;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@WebServlet(urlPatterns = "/orderHistory")
public class OrderHistoryServletController extends HttpServlet {

    OrderHistoryBo orderHistoryBo = (OrderHistoryBo) BoFactory.getBoFactory().getBo(BoTypes.ORDERHISTORY);

    OrderBo orderBo = (OrderBo) BoFactory.getBoFactory().getBo(BoTypes.ORDER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderIdStr = req.getParameter("orderId");
        String dateStr = req.getParameter("data"); // Assuming this represents a date
        String itemPrice1 = req.getParameter("ItemPrice");
        String customerId = req.getParameter("customerId");
        String itemCode = req.getParameter("ItemCode");
        String orderQuantityStr = req.getParameter("OrderQuantity");
        String totalStr = req.getParameter("total");

        // Parse and convert parameters to appropriate data types
        int orderId = Integer.parseInt(orderIdStr);
        Date date = null; // Adjust format as necessary
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int orderQuantity = Integer.parseInt(orderQuantityStr);
        int ItemCode = Integer.parseInt(itemCode);

        CustomerDTO customerDTO = orderBo.searchByCustomerId(customerId);
        Customer customer = new Customer(customerDTO.getCustomerId(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(), customerDTO.getCustomerSalary(), customerDTO.getCustomerMobile(), customerDTO.getCustomerEmail());

        ItemDTO itemDTO = orderBo.searchByItemId(ItemCode);
        Item item = new Item(itemDTO.getItemCode(), itemDTO.getItemName(), itemDTO.getItemPrice(), itemDTO.getQuantity());

        OrderHistoryDTO orderHistoryDTO = new OrderHistoryDTO(orderId, date, itemPrice1, orderQuantity, totalStr, customer, item);

        boolean b = orderHistoryBo.saveOrderHistory(orderHistoryDTO);

        if (b){
            resp.setStatus(200);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Order Placed  successfully");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Item not found");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            List<OrderHistoryDTO> allOrderHistory = orderHistoryBo.getAllOrderHistory();



            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            for (OrderHistoryDTO orderHistoryDTO : allOrderHistory){
                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder().
                        add("orderId",orderHistoryDTO.getOrderId()).
                        add("date",orderHistoryDTO.getDate().toString()).
                        add("finalTotal",orderHistoryDTO.getFinalTotal()).
                        add("itemPrice",orderHistoryDTO.getItemPrice()).
                        add("orderQuantity",orderHistoryDTO.getOrderQuantity()).
                        add("customer_id",orderHistoryDTO.getCustomer().getCustomerId()).
                        add("item_code",orderHistoryDTO.getItem().getItemCode());

                        jsonArrayBuilder.add(jsonObjectBuilder);

            }
            resp.setContentType("application/json");
            resp.getWriter().write(jsonArrayBuilder.build().toString());




        }catch (Exception e ){
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
        }
    }
}
