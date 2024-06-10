package kg.syntaryska.tourservicemaster.services.impl;

import kg.syntaryska.tourservicemaster.enums.Status;
import kg.syntaryska.tourservicemaster.exceptions.BookingExceptions;
import kg.syntaryska.tourservicemaster.models.dtos.BookingDto;
import kg.syntaryska.tourservicemaster.models.entities.Booking;
import kg.syntaryska.tourservicemaster.models.entities.Tour;
import kg.syntaryska.tourservicemaster.repository.BookingRepository;
import kg.syntaryska.tourservicemaster.services.BookingService;
import kg.syntaryska.tourservicemaster.services.TourService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TourService tourService;

    public BookingServiceImpl(BookingRepository bookingRepository, TourService tourService) {
        this.bookingRepository = bookingRepository;
        this.tourService = tourService;
    }


    @Override
    public Booking bookTour(BookingDto bookingDto) {
        Tour tour = tourService.getTourById(bookingDto.tourId());

        if (tour == null) {
            throw new BookingExceptions("Error id is null");
        }

        Booking booking = new Booking();
        booking.setUserId(bookingDto.userId());
        booking.setTour(tour);
        booking.setBookingDate(bookingDto.bookingDate());
        booking.setStatus(Status.COMPLETED);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookings(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);

        return bookings;
    }


    @Override
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() ->
                new BookingExceptions("Booking not found"));

        booking.setStatus(Status.CANCELED);
        bookingRepository.save(booking);
    }
}
