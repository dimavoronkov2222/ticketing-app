package com.example.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.example.model.*;
@SpringBootTest
public class PlaceServiceTest {
    @Autowired
    private PlaceService placeService;
    @Test
    public void testCreatePlace() {
        Place place = placeService.createPlace("Stadium", "123 Main Street");
        assertNotNull(place);
        assertEquals("Stadium", place.getName());
        assertEquals("123 Main Street", place.getAddress());
    }
    @Test
    public void testFindPlaceById() {
        Place place = placeService.createPlace("Arena", "456 Another St");
        Place foundPlace = placeService.findPlaceById(place.getId());
        assertNotNull(foundPlace);
        assertEquals("Arena", foundPlace.getName());
    }
    @Test
    public void testFindPlaceByName() {
        Place place = placeService.createPlace("Stadium", "123 Main Street");
        Place foundPlace = placeService.findPlaceByName("Stadium");
        assertNotNull(foundPlace);
        assertEquals("123 Main Street", foundPlace.getAddress());
    }
}