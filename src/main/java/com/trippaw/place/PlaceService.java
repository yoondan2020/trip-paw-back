package com.trippaw.place;

import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }

    public void save(Place place){
        placeRepository.save(place);
    }
}
