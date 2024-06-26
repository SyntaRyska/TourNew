package kg.syntaryska.tourservicemaster.repository;

import kg.syntaryska.tourservicemaster.models.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
}
