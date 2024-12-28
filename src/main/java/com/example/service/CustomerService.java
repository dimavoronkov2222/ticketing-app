package com.example.service;
import com.example.model.Customer;
import com.example.model.Event;
import com.example.model.Ticket;
import com.example.repository.CustomerRepository;
import com.example.repository.EventRepository;
import com.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;
    public Customer createCustomer(String name, String email, String phone) {
        Customer customer = new Customer(name, email, phone);
        return customerRepository.save(customer);
    }
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    public Ticket assignTicketToCustomer(Long ticketId, Long customerId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        if (ticket.getStatus() == Ticket.Status.SOLD) {
            throw new RuntimeException("Ticket already sold");
        }
        ticket.setCustomer(customer);
        ticket.setStatus(Ticket.Status.SOLD);
        ticketRepository.save(ticket);
        return ticket;
    }
    public List<Ticket> findAvailableTicketsForEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        return event.getTickets().stream().filter(ticket -> ticket.getStatus() == Ticket.Status.FREE).collect(Collectors.toList());
    }
    public List<Event> findUpcomingEvents() {
        return eventRepository.findByEventDateAfter(LocalDateTime.now());
    }
}