package kg.syntaryska.tourservicemaster.controllers;

import kg.syntaryska.tourservicemaster.api_messages.ErrorMessage;
import kg.syntaryska.tourservicemaster.exceptions.TourExceptions;
import kg.syntaryska.tourservicemaster.models.entities.Tour;
import kg.syntaryska.tourservicemaster.models.dtos.TourDto;
import kg.syntaryska.tourservicemaster.services.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours/")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTours() {
        try {
            List<Tour> tours = tourService.getAllTours();
            return new ResponseEntity<>(tours, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.TOUR_CREATION_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable Long id) {
        Tour list = tourService.getTourById(id);
        if (list == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createTour(@RequestBody TourDto tourDto) {
        try {
            Tour tour = tourService.createTour(tourDto);
            return new ResponseEntity<>(tour, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.TOUR_CREATION_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTour(@PathVariable Long id, @RequestBody TourDto tourDto) {
        try {
            Tour tour = tourService.updateTour(id, tourDto);
            return new ResponseEntity<>(tour, HttpStatus.OK);
        } catch (TourExceptions e) {
            return new ResponseEntity<>(ErrorMessage.TOUR_UPDATE_ERROR + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.TOUR_CREATION_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTour(@PathVariable Long id) {
        Tour tour = tourService.getTourById(id);
        if (tour == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        tourService.deleteTour(id);
        return ResponseEntity.ok("Tour with id " + id + " deleted successfully");
    }

}
