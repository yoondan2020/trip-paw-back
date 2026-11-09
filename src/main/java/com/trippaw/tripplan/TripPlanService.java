package com.trippaw.tripplan;

import com.trippaw.user.User;
import com.trippaw.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripPlanService {

    private final TripPlanRepository tripPlanRepository;
    private final UserService userService;

    public TripPlan getPlan(Long planId) {
        return tripPlanRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("여행 계획을 찾을 수 없습니다."));
    }

    public TripPlan createPlan(TripPlanRequestDTO dto, Principal principal) {
        User creator = userService.findByPrincipal(principal);

        TripPlan tripPlan = new TripPlan();
        tripPlan.setName(dto.getName());
        tripPlan.setCreator(creator);

        return tripPlanRepository.save(tripPlan);
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
}
