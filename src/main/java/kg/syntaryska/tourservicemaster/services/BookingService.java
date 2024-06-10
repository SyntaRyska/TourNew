package kg.syntaryska.tourservicemaster.services;

import kg.syntaryska.tourservicemaster.models.dtos.BookingDto;
import kg.syntaryska.tourservicemaster.models.entities.Booking;

import java.util.List;

public interface BookingService {

    Booking bookTour (BookingDto bookingDto);
    List<Booking> getBookings (Long userId);
    void cancelBooking (Long id);

}
