package com.virtualgiving.controllers;

import com.virtualgiving.entities.OrganizationEntity;
import com.virtualgiving.services.OrganizationService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService){
        this.organizationService = organizationService;
    }

    @GetMapping
    public List<OrganizationEntity> getAll(){
        return organizationService.getAll();
    }

    @GetMapping("/{id}")
    public OrganizationEntity getById(@PathVariable Long id){
        return organizationService.getById(id);
    }

    @PostMapping
    public OrganizationEntity add(@Valid @RequestBody OrganizationEntity organizationEntity){
        return organizationService.add(organizationEntity);
    }

    @PutMapping("/{id}")
    public OrganizationEntity update(@PathVariable Long id, @Valid @RequestBody OrganizationEntity organizationEntity){
        return organizationService.updateById(id, organizationEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        organizationService.delete(id);
        return ResponseEntity.ok("Organization deleted successfully");
    }

}
