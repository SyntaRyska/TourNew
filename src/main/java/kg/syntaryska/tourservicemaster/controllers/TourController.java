package kg.syntaryska.tourservicemaster.controllers;

import kg.syntaryska.tourservicemaster.models.entities.Tour;
import kg.syntaryska.tourservicemaster.models.dtos.TourDto;
import kg.syntaryska.tourservicemaster.services.TourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tourList = tourService.getAllTours();

        if (tourList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tourList);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(tourList);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable Long id) {
        Tour tour = tourService.getTourById(id);

        if (tour == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tour);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(tour);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTour(@Validated @RequestBody TourDto tourDto) {
        try {
            Tour tour = tourService.createTour(tourDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(tour);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating tour: " + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTour(@PathVariable Long id, @RequestBody TourDto tourDto) {
        Tour updatedTour = tourService.updateTour(id, tourDto);

        if (updatedTour == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No,found tour with id: ");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedTour);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTour(@PathVariable Long id) {
        Tour tour = tourService.getTourById(id);

        if (tour == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No this id is null");
        }
        tourService.deleteTour(id);
        return ResponseEntity.ok("Tour with id " + id + " deleted successfully");
    }

}
