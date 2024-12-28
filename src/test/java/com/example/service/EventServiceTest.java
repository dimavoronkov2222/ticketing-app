package com.example.service;
import com.example.dto.EventCreationDTO;
import com.example.dto.PlaceDTO;
import com.example.model.Event;
import com.example.model.Place;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class EventServiceTest {
    @Autowired
    private EventService eventService;
    @Autowired
    private PlaceService placeService;
    private Place place;
    @BeforeEach
    public void setUp() {
        place = placeService.createPlace("Stadium", "123 Main Street");
    }
    @Test
    public void testCreateEvent() {
        EventCreationDTO eventDTO = new EventCreationDTO("Concert", "2024-12-30T18:00:00", 1L);
        eventDTO.setName("Concert");
        eventDTO.setEventDate(LocalDateTime.now().plusDays(1));
        eventDTO.setPlaceDTO(new PlaceDTO("Stadium", "123 Main Street"));
        eventDTO.setTicketPacks(Collections.emptyList());
        eventService.createEvent(eventDTO);
        List<Event> events = eventService.findEventsAtPlace(place.getId());
        assertFalse(events.isEmpty());
        Event event = events.get(0);
        assertEquals("Concert", event.getName());
        assertEquals(place.getId(), event.getPlace().getId());
    }
    @Test
    public void testFindEventById() {
        EventCreationDTO eventDTO = new EventCreationDTO("Concert", "2024-12-30T18:00:00", 1L);
        eventDTO.setName("Concert");
        eventDTO.setEventDate(LocalDateTime.now().plusDays(1));
        eventDTO.setPlaceDTO(new PlaceDTO("Stadium", "123 Main Street"));
        eventDTO.setTicketPacks(Collections.emptyList());
        eventService.createEvent(eventDTO);
        List<Event> events = eventService.findEventsAtPlace(place.getId());
        Event event = events.get(0);
        Event foundEvent = eventService.findEventById(event.getId());
        assertNotNull(foundEvent);
        assertEquals("Concert", foundEvent.getName());
    }
    @Test
    public void testFindUpcomingEvents() {
        EventCreationDTO eventDTO = new EventCreationDTO("Concert", "2024-12-30T18:00:00", 1L);
        eventDTO.setName("Concert");
        eventDTO.setEventDate(LocalDateTime.now().plusDays(1));
        eventDTO.setPlaceDTO(new PlaceDTO("Stadium", "123 Main Street"));
        eventDTO.setTicketPacks(Collections.emptyList());
        eventService.createEvent(eventDTO);
        List<Event> events = eventService.findUpcomingEvents();
        assertFalse(events.isEmpty());
        assertEquals("Concert", events.get(0).getName());
    }
    @Test
    public void testFindEventsAtPlace() {
        EventCreationDTO eventDTO = new EventCreationDTO("Concert", "2024-12-30T18:00:00", 1L);
        eventDTO.setName("Concert");
        eventDTO.setEventDate(LocalDateTime.now().plusDays(1));
        eventDTO.setPlaceDTO(new PlaceDTO("Stadium", "123 Main Street"));
        eventDTO.setTicketPacks(Collections.emptyList());
        eventService.createEvent(eventDTO);
        List<Event> events = eventService.findEventsAtPlace(place.getId());
        assertFalse(events.isEmpty());
        assertEquals("Concert", events.get(0).getName());
    }
}