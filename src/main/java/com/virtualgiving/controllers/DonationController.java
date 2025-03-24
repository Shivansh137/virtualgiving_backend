package com.virtualgiving.controllers;

import com.virtualgiving.entities.DonationEntity;
import com.virtualgiving.services.DonationService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/donations")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService){
        this.donationService = donationService;
    }

    @GetMapping
    public List<DonationEntity> getAllDonations(){
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public DonationEntity getDonationById(@PathVariable Long id){
        return donationService.getDonationById(id);
    }

    @PostMapping
    public DonationEntity addNewDonation(@Valid @RequestBody DonationEntity donationEntity){
        return donationService.addNewDonation(donationEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonationById(@PathVariable Long id){
        donationService.deleteDonationById(id);
        return ResponseEntity.ok("Donation deleted successfully");
    }
}
