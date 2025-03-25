package com.virtualgiving.services;

import com.virtualgiving.entities.AlumniEntity;
import com.virtualgiving.entities.InternshipOpportunityEntity;
import com.virtualgiving.enums.Status;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.AlumniRepository;
import com.virtualgiving.repositories.InternshipOpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipOpportunityService {

    @Autowired
    private final InternshipOpportunityRepository internshipRepository;
    private final AlumniRepository alumniRepository;

    public InternshipOpportunityService(
            InternshipOpportunityRepository internshipRepository,
            AlumniRepository alumniRepository) {
        this.internshipRepository = internshipRepository;
        this.alumniRepository = alumniRepository;
    }

    public InternshipOpportunityEntity addInternshipOpportunity(InternshipOpportunityEntity internship, Long alumniId) {
        AlumniEntity alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() -> new UserNotFoundException(alumniId));

        internship.setAlumni(alumni);
        internship.setStatus(Status.OPEN);  
        return internshipRepository.save(internship);
    }

    public List<InternshipOpportunityEntity> getAllInternshipOpportunities() {
        return internshipRepository.findAll();
    }

    public InternshipOpportunityEntity getInternshipOpportunityById(Long id) {
        return internshipRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public InternshipOpportunityEntity updateInternshipStatus(Long id, Status status) {
        InternshipOpportunityEntity internship = internshipRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        internship.setStatus(status);
        return internshipRepository.save(internship);
    }

    public void deleteInternshipOpportunity(Long id) {
        InternshipOpportunityEntity internship = internshipRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        internshipRepository.delete(internship);
    }
}
