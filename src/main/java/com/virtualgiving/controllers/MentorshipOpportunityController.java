package com.virtualgiving.controllers;

import com.virtualgiving.entities.MentorshipOpportunityEntity;
import com.virtualgiving.enums.Status;
import com.virtualgiving.services.MentorshipOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentorship_opportunities")
public class MentorshipOpportunityController {

    @Autowired
    private final MentorshipOpportunityService opportunityService;

    public MentorshipOpportunityController(MentorshipOpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @PostMapping
    public MentorshipOpportunityEntity addOpportunity(
            @RequestBody MentorshipOpportunityEntity opportunity,
            @PathVariable Long alumniId) {
        return opportunityService.addOpportunity(opportunity, alumniId);
    }

    @GetMapping
    public List<MentorshipOpportunityEntity> getAllOpportunities() {
        return opportunityService.getMentorshipOpportunities();
    }

    @GetMapping("/{id}")
    public MentorshipOpportunityEntity getOpportunityById(@PathVariable Long id) {
        return opportunityService.getOpportunityById(id);
    }

    @PostMapping("/{id}")
    public MentorshipOpportunityEntity applyForMentorship(
            @PathVariable Long opportunityId,
            @PathVariable Long studentId) {
        return opportunityService.applyForMentorship(opportunityId, studentId);
    }

    @PutMapping("/{id}")
    public MentorshipOpportunityEntity updateOpportunityStatus(
            @PathVariable Long id,
            @RequestParam Status status) {
        return opportunityService.updateOpportunityStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteOpportunity(@PathVariable Long id) {
        opportunityService.deleteOpportunity(id);
    }
}
