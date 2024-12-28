package com.example.controller;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/create")
    public void createCustomer(@RequestParam String name, @RequestParam String email, @RequestParam String phone) {
        customerService.createCustomer(name, email, phone);
    }
    @PostMapping("/assignTicket")
    public void assignTicketToCustomer(@RequestParam Long ticketId, @RequestParam Long customerId) {
        customerService.assignTicketToCustomer(ticketId, customerId);
    }
}