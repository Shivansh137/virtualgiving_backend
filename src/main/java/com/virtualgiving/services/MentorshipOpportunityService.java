package com.virtualgiving.services;

import com.virtualgiving.entities.AlumniEntity;
import com.virtualgiving.entities.MentorshipOpportunityEntity;
import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.enums.Status;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.AlumniRepository;
import com.virtualgiving.repositories.MentorshipOpportunityRepository;
import com.virtualgiving.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorshipOpportunityService {

    @Autowired
    private final MentorshipOpportunityRepository opportunityRepository;
    private final AlumniRepository alumniRepository;
    private final StudentRepository studentRepository;

    public MentorshipOpportunityService(
            MentorshipOpportunityRepository opportunityRepository,
            AlumniRepository alumniRepository,
            StudentRepository studentRepository) {
        this.opportunityRepository = opportunityRepository;
        this.alumniRepository = alumniRepository;
        this.studentRepository = studentRepository;
    }

    public MentorshipOpportunityEntity addOpportunity(MentorshipOpportunityEntity opportunity, Long alumniId) {
        AlumniEntity alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() -> new UserNotFoundException(alumniId));

        opportunity.setAlumni(alumni);
        opportunity.setStatus(Status.OPEN);  
        return opportunityRepository.save(opportunity);
    }

    public List<MentorshipOpportunityEntity> getMentorshipOpportunities() {
        return opportunityRepository.findAll();
    }

    public MentorshipOpportunityEntity getOpportunityById(Long id) {
        return opportunityRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public MentorshipOpportunityEntity applyForMentorship(Long opportunityId, Long studentId) {
        MentorshipOpportunityEntity opportunity = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new UserNotFoundException(opportunityId));
    
        StudentEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException(studentId));
    
        if (opportunity.getStatus() == Status.FILLED) {
            throw new IllegalStateException("This mentorship opportunity is already filled.");
        }
    
        opportunity.setStatus(Status.FILLED);
        opportunity.setStudent(student);  
    
        return opportunityRepository.save(opportunity);
    }
    

    public MentorshipOpportunityEntity updateOpportunityStatus(Long id, Status status) {
        MentorshipOpportunityEntity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        opportunity.setStatus(status);
        return opportunityRepository.save(opportunity);
    }

    public void deleteOpportunity(Long id) {
        MentorshipOpportunityEntity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        opportunityRepository.delete(opportunity);
    }
}
