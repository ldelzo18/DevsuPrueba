package com.devsu.apirest.integration;

import com.devsu.apirest.dto.customer.CustomerDTO;
import com.devsu.apirest.model.Customer;
import com.devsu.apirest.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testCreateCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder().name("Test Person")
                .gender("Male")
                .age(20)
                .identification("ID-82932131")
                .address("Jr. Rosa Toro 234")
                .phone("503435342")
                .password("Th1s1s4np4ssw0rd")
                .status(true).build();

        ResponseEntity<CustomerDTO> response = restTemplate.postForEntity("/clientes/", customerDTO, CustomerDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        Optional<Customer> savedCustomer = customerRepository.findByIdentification(customerDTO.getIdentification());
        assertNotNull(savedCustomer);

        customerRepository.delete(savedCustomer.get());
    }

}
