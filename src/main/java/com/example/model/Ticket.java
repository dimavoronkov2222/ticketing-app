package com.example.model;
import jakarta.persistence.*;
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double cost;
    private Integer number;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        FREE, SOLD
    }
    public Ticket() {
        this.status = Status.FREE;
    }
    public Ticket(Double cost, Integer number, Event event) {
        this.cost = cost;
        this.number = number;
        this.event = event;
        this.status = Status.FREE;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}