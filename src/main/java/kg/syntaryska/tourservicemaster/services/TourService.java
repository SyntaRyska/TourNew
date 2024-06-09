package kg.syntaryska.tourservicemaster.services;

import kg.syntaryska.tourservicemaster.models.Tour;
import kg.syntaryska.tourservicemaster.models.dtos.TourDto;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();

    Tour getTourById(Long id);

    Tour createTour(TourDto tourDto);

    Tour updateTour(Long id, TourDto tourDto);

    void deleteTour(Long id);
}
