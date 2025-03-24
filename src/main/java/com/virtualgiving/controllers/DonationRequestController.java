package com.virtualgiving.controllers;

import com.virtualgiving.entities.DonationRequestEntity;
import com.virtualgiving.services.DonationRequestService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation_requests")
public class DonationRequestController {
    private final DonationRequestService donationRequestService;

    public DonationRequestController(DonationRequestService donationRequestService){
        this.donationRequestService = donationRequestService;
    }

    @GetMapping
    public List<DonationRequestEntity> getAllDonationRequests(){
        return donationRequestService.getAllDonationRequests();
    }

    @GetMapping("/{id}")
    public DonationRequestEntity getDonationRequestById(@PathVariable Long id){
        return donationRequestService.getDonationRequestById(id);
    }

    @PostMapping
    public DonationRequestEntity addNewDonationRequest(@Valid @RequestBody DonationRequestEntity donationRequestEntity){
        return donationRequestService.addNewDonationRequest(donationRequestEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonationRequestById(@PathVariable Long id){
        donationRequestService.deleteDonationRequestById(id);
        return ResponseEntity.ok("Donation Request deleted successfully");
    }
}
