package com.virtualgiving.controllers;

import com.virtualgiving.entities.OrganizationEntity;
import com.virtualgiving.services.OrganizationService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @GetMapping
    public List<OrganizationEntity> getAllOrganizations(){
        return organizationService.getAllOrganizations();
    }

    @GetMapping("/{id}")
    public OrganizationEntity getOrganizationById(@PathVariable Long id){
        return organizationService.getOrganizationById(id);
    }

    @PostMapping
    public OrganizationEntity addNewOrganization(@Valid @RequestBody OrganizationEntity organizationEntity){
        return organizationService.addNewOrganization(organizationEntity);
    }

    @PutMapping("/{id}")
    public OrganizationEntity updateOrganizationById(@PathVariable Long id, @Valid @RequestBody OrganizationEntity organizationEntity){
        return organizationService.updateOrganizationById(id, organizationEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganizationById(@PathVariable Long id){
        organizationService.deleteOrganizationById(id);
        return ResponseEntity.ok("Organization deleted successfully");
    }

}
