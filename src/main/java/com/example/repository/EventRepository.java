package com.example.repository;
import com.example.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByPlaceId(Long placeId);
    List<Event> findByEventDateAfter(LocalDateTime date);
}