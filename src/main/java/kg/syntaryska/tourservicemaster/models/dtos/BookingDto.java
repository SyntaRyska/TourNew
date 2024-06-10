package kg.syntaryska.tourservicemaster.models.dtos;

import kg.syntaryska.tourservicemaster.enums.Status;

import java.util.Date;

public record BookingDto(
        Long id,
        Long userId,
        Long tourId,
        Date bookingDate,
        Status status
) {}
