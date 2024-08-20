package com.devsu.apirest2.service.impl;

import com.devsu.apirest2.service.CustomerService;
import com.devsu.apirest2.exception.ObjectNotPresentException;
import com.devsu.apirest2.model.Customer;
import com.devsu.apirest2.repository.CustomerRepository;
import com.devsu.apirest2.util.ErrorMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerIfExists(Long customerId){
        LOGGER.info("Starting find customer");
        try{
            return customerRepository.findById(customerId).orElseThrow(
                    () -> new ObjectNotPresentException(ErrorMessages.CUSTOMER_NOT_FOUND)
            );
        } finally {
            LOGGER.info("Finish processing find customer");
        }

    }

}
