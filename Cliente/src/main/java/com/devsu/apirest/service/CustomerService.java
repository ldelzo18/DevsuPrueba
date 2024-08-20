package com.devsu.apirest.service;

import com.devsu.apirest.dto.customer.CustomerDTO;
import com.devsu.apirest.dto.customer.CustomerPatchDTO;
import com.devsu.apirest.model.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO deleteCustomer(Long customerId);
    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);
    CustomerDTO partialUpdateCustomer(Long customerId, CustomerPatchDTO customerPatchDTO);
    Customer findCustomerIfExists(Long customerId);

}
