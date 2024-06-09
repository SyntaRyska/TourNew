package kg.syntaryska.tourservicemaster.models.dtos;

import jakarta.persistence.Column;

import java.util.Date;

public record TourDto(

        Long id,

        String title,

        String description,

        String country,

        int duration,

        double price,

        @Column(name = "date_tour")
        Date dateTour
) {}

