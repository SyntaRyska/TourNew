package kg.syntaryska.tourservicemaster.controllers;

import kg.syntaryska.tourservicemaster.models.dtos.BookingDto;
import kg.syntaryska.tourservicemaster.models.entities.Booking;
import kg.syntaryska.tourservicemaster.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingDto booking) {
        Booking bookingNew = bookingService.bookTour(booking);
        return ResponseEntity.ok(bookingNew);
    }

    @GetMapping("getById/{userId}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookings(userId);

        if (bookings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().build();
    }
}
