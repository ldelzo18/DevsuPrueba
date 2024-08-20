package com.devsu.apirest.controller;

import com.devsu.apirest.dto.customer.CustomerDTO;
import com.devsu.apirest.dto.customer.CustomerPatchDTO;
import com.devsu.apirest.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        LOGGER.info("Receive a get all customers petition");
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(value="customerId") Long customerId){
        LOGGER.info("Receive a get customer petition for customer id {}", customerId);
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDTO> insertCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        LOGGER.info("Receive a save customer petition");
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> deleteCustomerById(@PathVariable(value="customerId") Long customerId){
        LOGGER.info("Receive a delete customer petition for customer id {}", customerId);
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable(value="customerId") Long customerId,
                                                      @Valid @RequestBody CustomerDTO customerDTO){
        LOGGER.info("Receive a update customer petition for customer id {}", customerId);
        return new ResponseEntity<>(customerService.updateCustomer(customerId, customerDTO), HttpStatus.OK);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> partialUpdateCustomer(@PathVariable(value="customerId") Long customerId,
                                                             @RequestBody CustomerPatchDTO customerPatchDTO){
        LOGGER.info("Receive a partial update customer petition for customer id {}", customerId);
        return new ResponseEntity<>(customerService.partialUpdateCustomer(customerId, customerPatchDTO), HttpStatus.OK);
    }
}
