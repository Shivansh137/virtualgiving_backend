package com.virtualgiving.services;

import com.virtualgiving.entities.DonationEntity;
import com.virtualgiving.entities.DonationRequestEntity;
import com.virtualgiving.entities.AlumniEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.DonationRepository;
import com.virtualgiving.repositories.DonationRequestRepository;
import com.virtualgiving.repositories.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    private final DonationRepository donationRepository;
    private final AlumniRepository alumniRepository;
    private final DonationRequestRepository donationRequestRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository, AlumniRepository alumniRepository, DonationRequestRepository donationRequestRepository){
        this.donationRepository = donationRepository;
        this.alumniRepository = alumniRepository;
        this.donationRequestRepository = donationRequestRepository;
    }

    public DonationEntity addNewDonation(DonationEntity donationEntity){
        AlumniEntity alumniEntity = alumniRepository.findById(donationEntity.getDonor().getId()).orElseThrow(()-> new RuntimeException("Mentor not found"));
            donationEntity.setDonor(alumniEntity);
            DonationRequestEntity donationRequestEntity = donationRequestRepository.findById(donationEntity.getRequest().getId()).orElseThrow(()-> new RuntimeException("DonationRequest not found"));
            donationEntity.setRequest(donationRequestEntity);
            return donationRepository.save(donationEntity);
    }

    public List<DonationEntity> getAllDonations(){
        return donationRepository.findAll();
    }

    public DonationEntity getDonationById(Long id){
        return donationRepository.findById(id).orElseThrow(() -> new RuntimeException("Donation Not Found"));
    }

    public void deleteDonationById(Long id){
        DonationEntity donationEntity = donationRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        donationRepository.delete(donationEntity);
    }
}
