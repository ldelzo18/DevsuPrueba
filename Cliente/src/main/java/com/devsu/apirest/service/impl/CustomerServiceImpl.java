package com.devsu.apirest.service.impl;

import com.devsu.apirest.dto.customer.CustomerDTO;
import com.devsu.apirest.dto.customer.CustomerPatchDTO;
import com.devsu.apirest.exception.DuplicateException;
import com.devsu.apirest.exception.ObjectNotPresentException;
import com.devsu.apirest.model.Customer;
import com.devsu.apirest.repository.CustomerRepository;
import com.devsu.apirest.service.CustomerService;
import com.devsu.apirest.util.ErrorMessages;
import com.devsu.apirest.util.mapper.CustomerMapper;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        LOGGER.info("Starting get all customers request");
        try{
            List<Customer> customerList = customerRepository.findAll();
            return customerList.stream().map(CustomerMapper::customerToCustomerDTO).toList();
        } catch (Exception ex){
            LOGGER.error("Exception while processing get all customers, {}", ex.getMessage());
            return Collections.emptyList();
        } finally {
            LOGGER.info("Finish processing get all customer request");
        }
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        LOGGER.info("Starting get customer by id");
        try{
            return CustomerMapper.customerToCustomerDTO(findCustomerIfExists(customerId));
        } finally {
            LOGGER.info("Finish processing get customer by id");
        }

    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        LOGGER.info("Starting create customer");
        try{
            customerRepository.findByIdentification(customerDTO.getIdentification()).ifPresent(customer -> {
                throw new DuplicateException(ErrorMessages.CUSTOMER_IDENTIFICATION_EXISTS);
            });
            return CustomerMapper.customerToCustomerDTO(
                    customerRepository.save(CustomerMapper.customerDTOToCustomer(customerDTO)
            ));
        } finally {
            LOGGER.info("Finish processing create customer");
        }
    }

    @Override
    public CustomerDTO deleteCustomer(Long customerID) {
        LOGGER.info("Starting delete customer");
        try{
            Customer customer = findCustomerIfExists(customerID);
            customerRepository.delete(customer);
            return CustomerMapper.customerToCustomerDTO(customer);
        } finally {
            LOGGER.info("Finish processing delete customer");
        }
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        LOGGER.info("Starting update customer");
        try{
            customerDTO.setCustomerId(customerId);
            findCustomerIfExists(customerId);
            customerRepository.save(CustomerMapper.updateCustomerFromCustomerDTO(customerDTO));
            return customerDTO;
        } finally {
            LOGGER.info("Finish processing update customer");
        }
    }

    @Override
    public CustomerDTO partialUpdateCustomer(Long customerID, CustomerPatchDTO customerPatchDTO) {
        LOGGER.info("Starting partial update customer");
        try{
            Customer customer = CustomerMapper.partialUpdateCustomerFromPatchCustomer(customerPatchDTO,
                    findCustomerIfExists(customerID));
            return CustomerMapper.customerToCustomerDTO(customerRepository.save(customer));
        } finally {
            LOGGER.info("Finish processing partial update customer");
        }
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
