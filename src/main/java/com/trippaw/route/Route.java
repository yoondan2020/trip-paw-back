package com.trippaw.route;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name = "route"
)
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
