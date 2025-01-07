package org.example.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private int customerId;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerSalary;
    private String CustomerMobile;
    private String CustomerEmail;

    public CustomerDTO(String name, String address, String salary, String mobile, String email) {
        this.CustomerName = name;
        this.CustomerAddress= address;
        this.CustomerSalary = salary;
        this.CustomerMobile = mobile;
        this.CustomerEmail= email;
    }

}
