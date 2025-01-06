package org.example.bo.BO;

import org.example.bo.SuperBO;
import org.example.dto.CustomerDTO;

import java.sql.ResultSet;
import java.util.List;

public interface CustomerBo extends SuperBO {
    boolean SaveCustomer(CustomerDTO customerDTO);

    boolean updateCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomer();

    boolean deleteCustomer(int customerId);
}
