package com.virtualgiving.services;

import com.virtualgiving.entities.DonationRequestEntity;
import com.virtualgiving.entities.OrganizationEntity;
import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.DonationRequestRepository;
import com.virtualgiving.repositories.OrganizationRepository;
import com.virtualgiving.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationRequestService {
    private final DonationRequestRepository donationRequestRepository;
    private final StudentRepository studentRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public DonationRequestService(DonationRequestRepository donationRequestRepository, StudentRepository studentRepository, OrganizationRepository organizationRepository){
        this.donationRequestRepository = donationRequestRepository;
        this.studentRepository = studentRepository;
        this.organizationRepository = organizationRepository;
    }

    public DonationRequestEntity addNewDonationRequest(DonationRequestEntity donationRequestEntity){
        if(donationRequestEntity.getStudent() != null){
            StudentEntity studentEntity = studentRepository.findById(donationRequestEntity.getStudent().getId()).orElseThrow(()-> new RuntimeException("Student not found"));
            donationRequestEntity.setStudent(studentEntity);
        }else if(donationRequestEntity.getOrganization() != null){
            OrganizationEntity organizationEntity = organizationRepository.findById(donationRequestEntity.getOrganization().getId()).orElseThrow(()-> new RuntimeException("Organization not found"));
            donationRequestEntity.setOrganization(organizationEntity);
        }
        return donationRequestRepository.save(donationRequestEntity);
    }

    public List<DonationRequestEntity> getAllDonationRequests(){
        return donationRequestRepository.findAll();
    }

    public DonationRequestEntity getDonationRequestById(Long id){
        return donationRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank Details Not Found"));
    }

    public void deleteDonationRequestById(Long id){
        DonationRequestEntity donationRequestEntity = donationRequestRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        donationRequestRepository.delete(donationRequestEntity);
    }
}
