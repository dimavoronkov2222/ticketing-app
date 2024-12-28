package com.example.service;
import com.example.dto.EventCreationDTO;
import com.example.dto.PlaceDTO;
import com.example.dto.TicketPackDTO;
import com.example.exception.TicketAlreadySoldException;
import com.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class TicketServiceTest {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EventService eventService;
    private Customer customer;
    private Event event;
    private Ticket ticket;
    @BeforeEach
    public void setUp() {
        customer = customerService.createCustomer("John Doe", "john.doe@example.com", "123456789");
        EventCreationDTO eventDTO = new EventCreationDTO("Concert", "2024-12-30T18:00:00", 1L);
        eventDTO.setName("Concert");
        eventDTO.setEventDate(LocalDateTime.now().plusDays(1));
        eventDTO.setPlaceDTO(new PlaceDTO("Stadium", "123 Main Street"));
        eventDTO.setTicketPacks(List.of(new TicketPackDTO(100.0, 10)));
        eventService.createEvent(eventDTO);
        event = eventService.findEventsAtPlace(1L).get(0);
        ticket = ticketService.createTicket(event, 100.0, 1);
    }
    @Test
    public void testCreateTicket() {
        assertNotNull(ticket);
        assertEquals(100.0, ticket.getCost());
        assertEquals(1, ticket.getNumber());
    }
    @Test
    public void testAssignTicketToCustomer() {
        ticketService.assignTicketToCustomer(ticket.getId(), customer.getId());
        assertEquals(Ticket.Status.SOLD, ticket.getStatus());
        assertEquals(customer.getId(), ticket.getCustomer().getId());
    }
    @Test
    public void testAssignAlreadySoldTicket() {
        ticketService.assignTicketToCustomer(ticket.getId(), customer.getId());
        assertThrows(TicketAlreadySoldException.class, () -> ticketService.assignTicketToCustomer(ticket.getId(), customer.getId()));
    }
    @Test
    public void testFindAvailableTickets() {
        List<Ticket> availableTickets = ticketService.findAvailableTickets(event.getId());
        assertFalse(availableTickets.isEmpty());
    }
}