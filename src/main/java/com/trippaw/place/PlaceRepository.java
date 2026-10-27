package com.trippaw.place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByname(String keyword);

    List<Place> findByAreacode(String areacode);

    List<Place> findByContentTypeId(String contentTypeId);
}
