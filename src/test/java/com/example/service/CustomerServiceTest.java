package com.example.service;
import com.example.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @BeforeEach
    public void setUp() {
    }
    @Test
    public void testCreateCustomer() {
        Customer customer = customerService.createCustomer("John Doe", "john.doe@example.com", "123456789");
        assertNotNull(customer);
        assertEquals("John Doe", customer.getName());
    }
    @Test
    public void testFindCustomerById() {
        Customer customer = customerService.createCustomer("Jane Doe", "jane.doe@example.com", "987654321");
        Optional<Customer> foundCustomer = customerService.findCustomerById(customer.getId());
        assertNotNull(foundCustomer);
        assertEquals("Jane Doe", foundCustomer.get().getName());
    }
    @Test
    public void testFindCustomerByEmail() {
        customerService.createCustomer("John Doe", "john.doe@example.com", "123456789");
        Optional<Customer> customer = customerService.findCustomerByEmail("john.doe@example.com");
        assertNotNull(customer);
        assertEquals("john.doe@example.com", customer.get().getEmail());
    }
}