package com.example.controller;
import com.example.dto.EventCreationDTO;
import com.example.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(EventController.class)
class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createEvent_ShouldReturnOk() throws Exception {
        EventCreationDTO eventDTO = new EventCreationDTO("Concert", "2024-12-30T18:00:00", 1L);

        mockMvc.perform(post("/events/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(eventDTO)))
                .andExpect(status().isOk());

        Mockito.verify(eventService).createEvent(Mockito.any(EventCreationDTO.class));
    }
}