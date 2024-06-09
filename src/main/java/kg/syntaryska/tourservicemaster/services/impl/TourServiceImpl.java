package kg.syntaryska.tourservicemaster.services.impl;

import kg.syntaryska.tourservicemaster.exceptions.TourExceptions;
import kg.syntaryska.tourservicemaster.models.Tour;
import kg.syntaryska.tourservicemaster.models.dtos.TourDto;
import kg.syntaryska.tourservicemaster.repository.TourRepository;
import kg.syntaryska.tourservicemaster.services.TourService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll().stream()
                .filter(tour -> tour.getCountry().equalsIgnoreCase("Кыргызстан"))
                .collect(Collectors.toList());
    }

    @Override
    public Tour getTourById(Long id) {
        return tourRepository.findById(id).orElseThrow(() -> new TourExceptions("This tour id is not found"));
    }

    @Override
    public Tour createTour(TourDto tourDto) {
        Tour tour = new Tour();
        tour.setTitle(tourDto.title());
        tour.setDescription(tourDto.description());
        tour.setCountry(tourDto.country());
        tour.setDuration(tourDto.duration());
        tour.setPrice(tourDto.price());
        tour.setDateTour(tourDto.dateTour());

        return tourRepository.save(tour);
    }

    @Override
    public Tour updateTour(Long id, TourDto tourDto) {
        Tour tourZip = tourRepository.findById(id).orElseThrow(() -> new TourExceptions("Error please check tour id " + id));

        tourZip.setTitle(tourDto.title());
        tourZip.setDescription(tourDto.description());
        tourZip.setCountry(tourDto.country());
        tourZip.setDuration(tourDto.duration());
        tourZip.setPrice(tourDto.price());
        tourZip.setDateTour(tourDto.dateTour());
        return tourRepository.save(tourZip);
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }
}
