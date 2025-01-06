package org.example.bo.impl;

import org.example.bo.BO.CustomerBo;
import org.example.dao.DaoFactory;
import org.example.dao.DaoTypes;
import org.example.dao.dao.CustomerDao;
import org.example.dto.CustomerDTO;
import org.example.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {


    CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoFactory().getDao(DaoTypes.CUSTOMER);
    @Override
    public boolean SaveCustomer(CustomerDTO customerDTO) {
      return   customerDao.save(new Customer(customerDTO.getCustomerName(),customerDTO.getCustomerAddress(),customerDTO.getCustomerSalary(),customerDTO.getCustomerMobile(),customerDTO.getCustomerEmail()));

    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
       return customerDao.update(new Customer(customerDTO.getCustomerId(),customerDTO.getCustomerName(),customerDTO.getCustomerAddress(),customerDTO.getCustomerSalary(),customerDTO.getCustomerMobile(),customerDTO.getCustomerEmail()));
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> allCustomer = customerDao.getAll();

        List<CustomerDTO> customerDTOS = new ArrayList<>();


        for (Customer customer : allCustomer){
            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setCustomerAddress(customer.getCustomerAddress());
            customerDTO.setCustomerSalary(customer.getCustomerSalary());
            customerDTO.setCustomerMobile(customer.getCustomerMobile());
            customerDTO.setCustomerEmail(customer.getCustomerEmail());

            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
      return  customerDao.delete(customerId);
    }
}
