package org.example.controller.rest;

import org.example.model.DTO.TypeDTO;
import org.example.model.Tour;
import org.example.service.ITourService;
import org.example.service.imp.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tour")

public class RestTourController {
    @Autowired
    private final ITourService tourService;

    public RestTourController(ITourService tourService) {
        this.tourService = new TourService();
    }

    @GetMapping
    public ResponseEntity<Iterable<Tour>> findAllTour() {
        Iterable<Tour> tours = tourService.findAll();
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tour> findTourById(@PathVariable Long id) {
        Optional<Tour> tourOptional = tourService.findById(id);
        if (!tourOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tourOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour tour) {
        Optional<Tour> tourOptional = tourService.findById(id);
        if (!tourOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        tour.setId(tourOptional.get().getId());
        tourService.save(tour);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tour> deleteTour(@PathVariable Long id) {
        Optional<Tour> tourOptional = tourService.findById(id);
        if (!tourOptional.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        tourService.remove(id);
        return new ResponseEntity<>(tourOptional.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        tourService.save(tour);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
