package com.example.controller;
import com.example.dto.EventCreationDTO;
import com.example.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @PostMapping("/create")
    public void createEvent(@RequestBody EventCreationDTO eventDTO) {
        eventService.createEvent(eventDTO);
    }
}