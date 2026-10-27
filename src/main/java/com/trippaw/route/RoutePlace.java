package com.trippaw.route;

import com.trippaw.place.Place;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(
        name = "route_place"
)
@Getter
public class RoutePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    private Long sequence;

}
