package kg.syntaryska.tourservicemaster.models.entities;

import jakarta.persistence.*;
import kg.syntaryska.tourservicemaster.enums.Status;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    private Date bookingDate;

    @Enumerated(EnumType.STRING)
    private Status status;

}
