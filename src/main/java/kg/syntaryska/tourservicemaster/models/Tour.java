package kg.syntaryska.tourservicemaster.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tours")
public class Tour {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String country;

    private int duration;

    private double price;

    @Column(name = "date_tour")
    private Date dateTour;
}
