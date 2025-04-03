package com.virtualgiving.controllers;

import com.virtualgiving.entities.ApplicationEntity;
import com.virtualgiving.enums.Status;
import com.virtualgiving.services.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ApplicationEntity applyForInternship(@Valid @RequestBody ApplicationEntity applicationEntity) {
        return applicationService.applyForInternship(applicationEntity);
    }

    @GetMapping
    public List<ApplicationEntity> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public ApplicationEntity getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    // Delete an application by ID (optional, mainly for admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplicationById(@PathVariable Long id) {
        applicationService.deleteApplicationById(id);
        return ResponseEntity.ok("Application deleted successfully");
    }

    // Alumni approves/rejects an application
    @PutMapping("/{id}")
    public ApplicationEntity updateApplicationStatus(
        @PathVariable Long id,
        @RequestParam Long alumniId,
        @RequestParam Status status) {
        return applicationService.updateApplicationStatus(id, alumniId, status);
    }
}
