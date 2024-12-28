package com.example.service;
import com.example.dto.EventCreationDTO;
import com.example.dto.TicketPackDTO;
import com.example.model.Event;
import com.example.model.Place;
import com.example.model.Ticket;
import com.example.repository.EventRepository;
import com.example.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PlaceRepository placeRepository;
    public void createEvent(EventCreationDTO eventDTO) {
        Place place = placeRepository.findByName(eventDTO.getPlaceDTO().getName())
                .orElseGet(() -> placeRepository.save(new Place(
                        eventDTO.getPlaceDTO().getName(),
                        eventDTO.getPlaceDTO().getAddress()
                )));
        List<Ticket> tickets = createTickets(eventDTO.getTicketPacks(), null);
        Event event = new Event(eventDTO.getName(), eventDTO.getEventDate(), place, tickets);
        tickets = createTickets(eventDTO.getTicketPacks(), event);
        event.setTickets(tickets);
        eventRepository.save(event);
    }
    private List<Ticket> createTickets(List<TicketPackDTO> ticketPacks, Event event) {
        List<Ticket> tickets = new ArrayList<>();
        int ticketNumber = 1;
        for (TicketPackDTO pack : ticketPacks) {
            for (int i = 0; i < pack.getCount(); i++) {
                Ticket ticket = new Ticket();
                ticket.setEvent(event);
                ticket.setNumber(ticketNumber++);
                ticket.setCost(pack.getCost());
                ticket.setStatus(Ticket.Status.FREE);
                tickets.add(ticket);
            }
        }
        return tickets;
    }
    public Event findEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    }
    public List<Event> findUpcomingEvents() {
        return eventRepository.findAll().stream()
                .filter(event -> event.getEventDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
    public List<Event> findEventsAtPlace(Long placeId) {
        return eventRepository.findByPlaceId(placeId);
    }
}