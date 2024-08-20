package com.devsu.apirest2.service;

import com.devsu.apirest2.model.Customer;

public interface CustomerService {
    Customer findCustomerIfExists(Long customerId);

}
