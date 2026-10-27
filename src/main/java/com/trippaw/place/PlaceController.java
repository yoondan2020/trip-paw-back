package com.trippaw.place;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaceController {

    private PlaceService placeService;

    public PlaceController(PlaceService placeService){
        this.placeService = placeService;
    }

    // P-001
    @GetMapping("/{placeId}")
    public Place findById(Long id){
        return placeService.findById(id);
    }

    // P-002
    @GetMapping("/")
    public List<Place> findByAll() {
        return placeService.findByAll();
    }

    // P-003
    @GetMapping("/search")
    public List<Place> findByKeyword(@RequestParam String keyword) {
        return placeService.findByKeyword(keyword);
    }

    // P-004
    @GetMapping("/area/{areaCode}")
    public List<Place> findByAreacode(String areaCode) {
        return placeService.findByAreacode(areaCode);
    }

    // P-005
    @GetMapping("/contentType/{contentTypeId}")
    public List<Place> findByContentTypeId(String contentTypeId){
        return placeService.findByContentTypeId(contentTypeId);
    }

    // P-006
    @GetMapping("/near")
    public List<Place> findByNearPlace(@RequestParam String mapx, @RequestParam String mapy) {
        return placeService.findByNearPlace(mapx, mapy);
    }

}
