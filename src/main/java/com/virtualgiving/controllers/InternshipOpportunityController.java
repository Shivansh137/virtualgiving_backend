package com.virtualgiving.controllers;

import com.virtualgiving.entities.InternshipOpportunityEntity;
import com.virtualgiving.enums.Status;
import com.virtualgiving.services.InternshipOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship_opportunities")
public class InternshipOpportunityController {

    @Autowired
    private final InternshipOpportunityService internshipService;

    public InternshipOpportunityController(InternshipOpportunityService internshipService) {
        this.internshipService = internshipService;
    }

    @PostMapping
    public InternshipOpportunityEntity addInternshipOpportunity(
            @RequestBody InternshipOpportunityEntity internship,
            @PathVariable Long alumniId) {
        return internshipService.addInternshipOpportunity(internship, alumniId);
    }

    @GetMapping
    public List<InternshipOpportunityEntity> getAllInternships() {
        return internshipService.getAllInternshipOpportunities();
    }

    @GetMapping("/{id}")
    public InternshipOpportunityEntity getInternshipById(@PathVariable Long id) {
        return internshipService.getInternshipOpportunityById(id);
    }

    @PutMapping("/{id}")
    public InternshipOpportunityEntity updateInternshipStatus(
            @PathVariable Long id,
            @RequestParam Status status) {
        return internshipService.updateInternshipStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteInternship(@PathVariable Long id) {
        internshipService.deleteInternshipOpportunity(id);
    }
}
