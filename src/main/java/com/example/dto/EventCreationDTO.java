package com.example.dto;
import java.util.List;
import java.time.LocalDateTime;
public class EventCreationDTO {
    private Long id;
    private LocalDateTime eventDate;
    private String name;
    private List<TicketPackDTO> ticketPacks;
    private PlaceDTO placeDTO;
    public EventCreationDTO(String concert, String s, long l) {
        this.name = name;
        this.eventDate = eventDate;
        this.ticketPacks = ticketPacks;
        this.placeDTO = placeDTO;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getEventDate() {
        return eventDate;
    }
    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<TicketPackDTO> getTicketPacks() {
        return ticketPacks;
    }
    public void setTicketPacks(List<TicketPackDTO> ticketPacks) {
        this.ticketPacks = ticketPacks;
    }
    public PlaceDTO getPlaceDTO() {
        return placeDTO;
    }
    public void setPlaceDTO(PlaceDTO placeDTO) {
        this.placeDTO = placeDTO;
    }
}
