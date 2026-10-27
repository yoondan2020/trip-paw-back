package com.trippaw.tripplan;

import com.trippaw.route.Route;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(
        name = "tripplan_route"
)
@Getter
public class TripPlanRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tripplan_id")
    private TripPlan tripPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

    private Long sequence;

}
