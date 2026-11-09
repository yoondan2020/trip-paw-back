package com.trippaw.tripplan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plans")
public class TripPlanController {

    private final TripPlanService tripPlanService;

    // T-001
    @GetMapping("/{planId}")
    public TripPlan getPlan(@PathVariable Long planId) {
        return tripPlanService.getPlan(planId);
    }

    // T-002
    @PostMapping
    public TripPlan createPlan(@RequestBody TripPlanRequestDTO dto, Principal principal) {
        return tripPlanService.createPlan(dto, principal);
    }

    // T-003
    @GetMapping
    public List<TripPlan> getPlans(Principal principal) {
        return tripPlanService.getPlans(principal);
    }

    // T-004
    @DeleteMapping("/{planId}")
    public void deletePlan(@PathVariable Long planId) {
        tripPlanService.deletePlan(planId);
    }

    // T-005
    @PatchMapping("/{planId}")
    public TripPlan patchPlan(@PathVariable Long planId, @RequestBody TripPlanRequestDTO dto) {
        return tripPlanService.patchPlan(planId, dto);
    }
}