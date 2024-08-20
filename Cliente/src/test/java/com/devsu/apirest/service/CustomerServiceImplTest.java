package com.devsu.apirest.service;

import com.devsu.apirest.dto.customer.CustomerDTO;
import com.devsu.apirest.exception.ObjectNotPresentException;
import com.devsu.apirest.model.Customer;
import com.devsu.apirest.repository.CustomerRepository;
import com.devsu.apirest.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerByIdWhenCustomerExists() {
        Long customerId = 9999L;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.getCustomerById(customerId);
        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());

        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void testGetCustomerByIdWhenCustomerDoesNotExist() {
        Long customerId = 9999L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotPresentException.class, () -> customerService.getCustomerById(customerId));
        verify(customerRepository, times(1)).findById(customerId);
    }
}
