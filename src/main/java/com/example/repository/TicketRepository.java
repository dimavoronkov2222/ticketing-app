package com.example.repository;
import com.example.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventIdAndStatus(Long eventId, Ticket.Status status);
    List<Ticket> findByCustomerId(Long customerId);
}