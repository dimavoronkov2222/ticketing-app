package com.example.repository;
import com.example.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByName(String name);
}