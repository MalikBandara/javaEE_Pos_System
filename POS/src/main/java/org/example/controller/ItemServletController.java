package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bo.BO.ItemBo;
import org.example.bo.BoFactory;
import org.example.bo.BoTypes;
import org.example.dto.ItemDTO;
import org.example.entity.Item;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/item/*")

public class ItemServletController extends HttpServlet {

    ItemBo itemBo = (ItemBo) BoFactory.getBoFactory().getBo(BoTypes.ITEM);




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();
            System.out.println("Path Info: " + pathInfo); // Debug log

            // Check if the path is for generating next ID
            if (pathInfo != null && pathInfo.equals("/next-id")) {
                int nextId = itemBo.generateNextId();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("{\"nextId\": " + nextId + "}");
            } else if (pathInfo == null || pathInfo.equals("/")) {
                // If no specific path or base path, fetch all items
                List<ItemDTO> allItems = itemBo.getAllItems();

                List<Item> itemList = new ArrayList<>();
                for (ItemDTO itemDTO : allItems) {
                    Item item = new Item();
                    item.setItemCode(itemDTO.getItemCode());
                    item.setItemName(itemDTO.getItemName());
                    item.setItemPrice(itemDTO.getItemPrice());
                    item.setQuantity(itemDTO.getQuantity());
                    itemList.add(item);
                }

                JsonArrayBuilder allItemsJson = Json.createArrayBuilder();
                for (Item item : itemList) {
                    JsonObjectBuilder itemJson = Json.createObjectBuilder();
                    itemJson.add("ItemCode", item.getItemCode());
                    itemJson.add("ItemName", item.getItemName());
                    itemJson.add("ItemPrice", item.getItemPrice());
                    itemJson.add("Quantity", item.getQuantity());
                    allItemsJson.add(itemJson);
                }

                resp.setContentType("application/json");
                resp.getWriter().write(allItemsJson.build().toString());
            } else {
                // Handle invalid or unsupported GET requests
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("{\"error\": \"Invalid endpoint\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"error\": \"Failed to process request\"}");
        }
    }





    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String itemCode = req.getParameter("ItemCode");
            String name = req.getParameter("ItemName");
            String itemPrice = req.getParameter("ItemPrice");
            String quantity = req.getParameter("Quantity");

            ItemDTO item = new ItemDTO(itemCode, name, itemPrice, quantity);

            boolean b = itemBo.saveItem(item);

            if (b) {
                resp.setStatus(200);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Item Save successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("Item not found");
            }


        }catch (Exception  e){
            throw new RuntimeException();
        }




    }
}
