package com.trippaw.place;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }

    public Place findById(Long id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("장소를 찾을 수 없습니다."));
    }

    public List<Place> findByAll() {
        return placeRepository.findAll();
    }

    public List<Place> findByKeyword(String keyword){
        return placeRepository.findByname(keyword);
    }

    public List<Place> findByAreacode(String areacode){
        return placeRepository.findByAreacode(areacode);
    }

    public List<Place> findByContentTypeId(String contentTypeId) {
        return placeRepository.findByContentTypeId(contentTypeId);
    }

    public List<Place> findByNearPlace(String mapx, String mapy) {
        return null;
    }

    public void save(Place place){
        placeRepository.save(place);
    }
}
