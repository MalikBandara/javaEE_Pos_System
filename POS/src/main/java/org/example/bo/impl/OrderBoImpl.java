package org.example.bo.impl;

import org.example.bo.BO.OrderBo;
import org.example.dao.DaoFactory;
import org.example.dao.DaoTypes;
import org.example.dao.dao.OrderDao;
import org.example.dto.CustomerDTO;
import org.example.dto.ItemDTO;
import org.example.dto.OrderDTO;
import org.example.entity.Customer;
import org.example.entity.Item;
import org.example.entity.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderBoImpl implements OrderBo {


    OrderDao orderDao = (OrderDao) DaoFactory.getDaoFactory().getDao(DaoTypes.ORDER);
    @Override
    public CustomerDTO searchByCustomerId(String customerId) {

        Customer allCustomers = orderDao.getAllCustomers(customerId);
        CustomerDTO customerDTO = new CustomerDTO(allCustomers.getCustomerId()
                , allCustomers.getCustomerName(),
                allCustomers.getCustomerAddress(),
                allCustomers.getCustomerSalary(),
                allCustomers.getCustomerMobile(),
                allCustomers.getCustomerEmail());

        return customerDTO;

    }

    @Override
    public ItemDTO searchByItemId(String itemCode) {
        Item allItems = orderDao.getAllItems(itemCode);
        ItemDTO itemDTO = new ItemDTO(allItems.getItemCode(), allItems.getItemName(), allItems.getItemPrice(), allItems.getQuantity());
        return itemDTO;
    }

    @Override
    public boolean saveOrders(OrderDTO orderDTO) {
        return orderDao.save(new Order(orderDTO.getOrderId(),orderDTO.getItemName(),orderDTO.getItemPrice(),orderDTO.getQuantity(),orderDTO.getSubtotal(),orderDTO.getCustomer()));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> all = orderDao.getAll();

        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : all){
            OrderDTO orderDTO = new OrderDTO(order.getOrderId(), order.getItemName(), order.getItemPrice(), order.getQuantity(), order.getSubtotal(), order.getCustomer());
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;

    }

}
