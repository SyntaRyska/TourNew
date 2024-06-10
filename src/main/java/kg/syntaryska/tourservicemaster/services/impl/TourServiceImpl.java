package kg.syntaryska.tourservicemaster.services.impl;

import kg.syntaryska.tourservicemaster.exceptions.TourExceptions;
import kg.syntaryska.tourservicemaster.models.entities.Tour;
import kg.syntaryska.tourservicemaster.models.dtos.TourDto;
import kg.syntaryska.tourservicemaster.repository.TourRepository;
import kg.syntaryska.tourservicemaster.services.TourService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public Tour getTourById(Long id) {
        Optional<Tour> idSave = tourRepository.findById(id);
        return idSave.orElseThrow(null);
    }

    @Override
    public Tour createTour(TourDto tourDto) {
        Tour tour = tourDto.convertToEntity();
        return tourRepository.save(tour);
    }

    @Override
    public Tour updateTour(Long id, TourDto tourDto) {
        Tour tourZip = tourRepository.findById(id)
                .orElseThrow(() -> new TourExceptions("Error please check tour id " + id));

        // Обновление свойств существующего объекта
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
