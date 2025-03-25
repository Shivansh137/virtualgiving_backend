package com.virtualgiving.services;

import com.virtualgiving.entities.ApplicationEntity;
import com.virtualgiving.entities.InternshipOpportunityEntity;
import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.entities.AlumniEntity;
import com.virtualgiving.enums.Status;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.ApplicationRepository;
import com.virtualgiving.repositories.InternshipOpportunityRepository;
import com.virtualgiving.repositories.StudentRepository;
import com.virtualgiving.repositories.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final InternshipOpportunityRepository internshipOpportunityRepository;
    private final AlumniRepository alumniRepository;

    public ApplicationService(ApplicationRepository applicationRepository, StudentRepository studentRepository,
                              InternshipOpportunityRepository internshipOpportunityRepository, AlumniRepository alumniRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.internshipOpportunityRepository = internshipOpportunityRepository;
        this.alumniRepository = alumniRepository;
    }

    public ApplicationEntity applyForInternship(ApplicationEntity applicationEntity) {
        StudentEntity student = studentRepository.findById(applicationEntity.getApplicant().getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        applicationEntity.setApplicant(student);

        InternshipOpportunityEntity opportunity = internshipOpportunityRepository.findById(applicationEntity.getOpportunity().getId())
                .orElseThrow(() -> new RuntimeException("Internship opportunity not found"));
        applicationEntity.setOpportunity(opportunity);

        applicationEntity.setStatus(Status.PENDING);
        return applicationRepository.save(applicationEntity);
    }

    public List<ApplicationEntity> getAllApplications() {
        return applicationRepository.findAll();
    }

    public ApplicationEntity getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application Not Found"));
    }

    public void deleteApplicationById(Long id) {
        ApplicationEntity applicationEntity = applicationRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        applicationRepository.delete(applicationEntity);
    }

    public ApplicationEntity updateApplicationStatus(Long id, Long alumniId, Status status) {
        ApplicationEntity applicationEntity = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application Not Found"));

        InternshipOpportunityEntity opportunity = applicationEntity.getOpportunity();
        AlumniEntity alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() -> new RuntimeException("Alumni Not Found"));

        if (!opportunity.getAlumni().getId().equals(alumni.getId())) {
            throw new RuntimeException("Only the alumni who posted the opportunity can update the status");
        }

        applicationEntity.setStatus(status);
        return applicationRepository.save(applicationEntity);
    }
}
