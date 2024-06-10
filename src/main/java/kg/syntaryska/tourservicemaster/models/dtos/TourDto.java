package kg.syntaryska.tourservicemaster.models.dtos;

import jakarta.persistence.Column;
import kg.syntaryska.tourservicemaster.models.entities.Tour;

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
) {
        public Tour convertToEntity() {
                Tour tour = new Tour();
                tour.setTitle(this.title);
                tour.setDescription(this.description);
                tour.setCountry(this.country);
                tour.setDuration(this.duration);
                tour.setPrice(this.price);
                tour.setDateTour(this.dateTour);
                return tour;
        }
}

