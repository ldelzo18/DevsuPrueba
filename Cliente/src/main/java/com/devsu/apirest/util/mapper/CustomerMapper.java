package com.devsu.apirest.util.mapper;

import com.devsu.apirest.dto.customer.CustomerDTO;
import com.devsu.apirest.dto.customer.CustomerPatchDTO;
import com.devsu.apirest.model.Customer;

public class CustomerMapper {

    public static CustomerDTO customerToCustomerDTO(Customer customer){
        return CustomerDTO.builder().customerId(customer.getCustomerId())
                .name(customer.getName())
                .gender(customer.getGender())
                .age(customer.getAge())
                .identification(customer.getIdentification())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .password(customer.getPassword())
                .status(customer.getStatus())
                .build();
    }

    public static Customer customerDTOToCustomer(CustomerDTO customerDTO){
        return Customer.builder().password(customerDTO.getPassword())
                .status(customerDTO.getStatus())
                .name(customerDTO.getName())
                .gender(customerDTO.getGender())
                .age(customerDTO.getAge())
                .identification(customerDTO.getIdentification())
                .address(customerDTO.getAddress())
                .phone(customerDTO.getPhone())
                .build();
    }

    public static Customer updateCustomerFromCustomerDTO(CustomerDTO customerDTO){
        Customer updatedCustomer = customerDTOToCustomer(customerDTO);
        updatedCustomer.setCustomerId(customerDTO.getCustomerId());
        return updatedCustomer;
    }

    /*
    * This code can be refactored using reflection, but it will involve change encapsulation principle and code readable
    * */
    public static Customer partialUpdateCustomerFromPatchCustomer(CustomerPatchDTO customerPatchDTO, Customer customer){
        if (customerPatchDTO.getName() != null) {
            customer.setName(customerPatchDTO.getName());
        }
        if (customerPatchDTO.getGender() != null) {
            customer.setGender(customerPatchDTO.getGender());
        }
        if (customerPatchDTO.getAge() != null) {
            customer.setAge(customerPatchDTO.getAge());
        }
        if (customerPatchDTO.getIdentification()!= null) {
            customer.setIdentification(customerPatchDTO.getIdentification());
        }
        if (customerPatchDTO.getAddress() != null) {
            customer.setAddress(customerPatchDTO.getAddress());
        }
        if (customerPatchDTO.getPhone() != null) {
            customer.setPhone(customerPatchDTO.getPhone());
        }
        if (customerPatchDTO.getPassword() != null) {
            customer.setPassword(customerPatchDTO.getPassword());
        }
        if (customerPatchDTO.getStatus() != null) {
            customer.setStatus(customerPatchDTO.getStatus());
        }
        return customer;
    }

}
