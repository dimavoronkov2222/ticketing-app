package com.example.service;
import com.example.exception.TicketAlreadySoldException;
import com.example.model.Customer;
import com.example.model.Event;
import com.example.model.Ticket;
import com.example.repository.CustomerRepository;
import com.example.repository.EventRepository;
import com.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EventRepository eventRepository;
    public Ticket createTicket(Event event, double cost, int number) {
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setCost(cost);
        ticket.setNumber(number);
        ticket.setStatus(Ticket.Status.FREE);
        return ticketRepository.save(ticket);
    }
    @Transactional
    public void assignTicketToCustomer(Long ticketId, Long customerId) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (ticketOptional.isPresent() && customerOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            Customer customer = customerOptional.get();
            if (ticket.getStatus() == Ticket.Status.SOLD) {
                throw new TicketAlreadySoldException("Ticket is already sold.");
            }
            ticket.setCustomer(customer);
            ticket.setStatus(Ticket.Status.SOLD);
            ticketRepository.save(ticket);
        } else {
            throw new IllegalArgumentException("Ticket or Customer not found");
        }
    }
    public List<Ticket> findAvailableTickets(Long eventId) {
        return ticketRepository.findByEventIdAndStatus(eventId, Ticket.Status.FREE);
    }
    public Ticket findTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
    }
    public List<Ticket> findTicketsByCustomer(Long customerId) {
        return ticketRepository.findByCustomerId(customerId);
    }
}