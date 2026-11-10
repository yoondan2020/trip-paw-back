package com.trippaw.place;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByname(String keyword);

    List<Place> findByAreacode(String areacode);

    List<Place> findByContentTypeId(String contentTypeId);

    @Query(value = """
        SELECT *
        FROM place
        WHERE content_type_id = :contentTypeId
        ORDER BY RAND()
        LIMIT 1
    """, nativeQuery = true)
    Place findRandomByContentTypeId(@Param("contentTypeId") String contentTypeId);

    @Query(value = """
    SELECT *
    FROM place
    WHERE content_type_id = :contentTypeId
        AND id NOT IN (:visitedPlaceIds)
    ORDER BY ST_Distance_Sphere(
        POINT(mapx, mapy),
        POINT(:mapx, :mapy)
    )
    LIMIT 1
    """, nativeQuery = true)
    Place findNearPlaceByContentTypeId(
            @Param("mapx") double mapx,
            @Param("mapy") double mapy,
            @Param(value = "contentTypeId") String contentTypeId,
            @Param(value = "visitedPlaceIds") List<Long> visitedPlaceIds
    );

}
