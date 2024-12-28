package com.example.service;
import com.example.model.Place;
import com.example.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    public Place createPlace(String name, String address) {
        Place place = new Place();
        place.setName(name);
        place.setAddress(address);
        return placeRepository.save(place);
    }
    public Place findPlaceById(Long id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));
    }
    public Place findPlaceByName(String name) {
        return placeRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));
    }
    public List<Place> findAllPlaces() {
        return placeRepository.findAll();
    }
}