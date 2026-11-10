package com.trippaw.tripplan;

import com.trippaw.place.Place;
import com.trippaw.place.PlaceService;
import com.trippaw.user.User;
import com.trippaw.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripPlanService {

    private final TripPlanRepository tripPlanRepository;
    private final UserService userService;
    private final PlaceService placeService;

    public TripPlan getPlan(Long planId) {
        return tripPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("여행 계획을 찾을 수 없습니다."));
    }

    // T-002
    public TripPlan createPlan(TripPlanRequestDTO dto) {

        int days = dto.getNights() + 1;
        if(days >= 2) {

            List<Long> visitedPlaceIds = new ArrayList<>();

            Place accommodation = placeService.findRandomByContentTypeId("32");
            visitedPlaceIds.add(accommodation.getId());

            Place place3 = placeService.findNearPlaceByContentTypeId(
                    accommodation.getMapx(), accommodation.getMapy(), "12", visitedPlaceIds
            );
            visitedPlaceIds.add(place3.getId());

            Place place2 = placeService.findNearPlaceByContentTypeId(
                    place3.getMapx(), place3.getMapy(), "39",  visitedPlaceIds
            );
            visitedPlaceIds.add(place2.getId());

            Place place1 = placeService.findNearPlaceByContentTypeId(
                    place2.getMapx(), place2.getMapy(), "12", visitedPlaceIds
            );
            visitedPlaceIds.add(place1.getId());

            System.out.println("결과물: " + accommodation +  place3 + place2 + place1);

        }


        TripPlan tripPlan = new TripPlan();


        return null;
    }

    public List<TripPlan> getPlans(Principal principal) {
        User creator = userService.findByPrincipal(principal);
        return tripPlanRepository.findByCreatorId(creator.getId());
    }

    public void deletePlan(Long planId) {
        if (!tripPlanRepository.existsById(planId)) {
            throw new RuntimeException("여행 계획을 찾을 수 없습니다.");
        }
        tripPlanRepository.deleteById(planId);
    }

    @Transactional
    public TripPlan patchPlan(Long planId, TripPlanRequestDTO dto) {
        TripPlan tripPlan = getPlan(planId);

        if (dto.getName() != null) {
            tripPlan.setName(dto.getName());
        }

        return tripPlanRepository.save(tripPlan);
    }

    @Transactional
    public TripPlan savePlan(TripPlanRequestDTO dto, Principal principal) {

        User creator = userService.findByPrincipal(principal);

        TripPlan tripPlan = new TripPlan();
        tripPlan.setName(dto.getName());
        tripPlan.setCreator(creator);

        return tripPlanRepository.save(tripPlan);
    }

}
