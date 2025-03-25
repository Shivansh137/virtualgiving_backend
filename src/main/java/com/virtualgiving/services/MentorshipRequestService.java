package com.virtualgiving.services;

import com.virtualgiving.entities.AlumniEntity;
import com.virtualgiving.entities.MentorshipRequestEntity;
import com.virtualgiving.entities.OrganizationEntity;
import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.enums.Status;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.AlumniRepository;
import com.virtualgiving.repositories.MentorshipRequestRepository;
import com.virtualgiving.repositories.OrganizationRepository;
import com.virtualgiving.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorshipRequestService {
    @Autowired
    private final MentorshipRequestRepository mentorshipRequestRepository;
    private final StudentRepository studentRepository;
    private final OrganizationRepository organizationRepository;
    private final AlumniRepository alumniRepository;

    public MentorshipRequestService(MentorshipRequestRepository mentorshipRequestRepository,
                                    StudentRepository studentRepository,
                                    OrganizationRepository organizationRepository,
                                    AlumniRepository alumniRepository) {
        this.mentorshipRequestRepository = mentorshipRequestRepository;
        this.studentRepository = studentRepository;
        this.organizationRepository = organizationRepository;
        this.alumniRepository = alumniRepository;
    }

    public MentorshipRequestEntity addNewMentorshipRequest(MentorshipRequestEntity mentorshipRequestEntity) {
        if (mentorshipRequestEntity.getStudent() != null) {
            StudentEntity studentEntity = studentRepository.findById(mentorshipRequestEntity.getStudent().getId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            mentorshipRequestEntity.setStudent(studentEntity);
        } else if (mentorshipRequestEntity.getOrganization() != null) {
            OrganizationEntity organizationEntity = organizationRepository.findById(mentorshipRequestEntity.getOrganization().getId())
                    .orElseThrow(() -> new RuntimeException("Organization not found"));
            mentorshipRequestEntity.setOrganization(organizationEntity);
        }
        return mentorshipRequestRepository.save(mentorshipRequestEntity);
    }

    public List<MentorshipRequestEntity> getAllMentorshipRequests() {
        return mentorshipRequestRepository.findAll();
    }

    // Getting a mentorship request by ID
    public MentorshipRequestEntity getMentorshipRequestById(Long id) {
        return mentorshipRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentorship Request Not Found"));
    }

    // Alumni accepting a mentorship request
    public MentorshipRequestEntity acceptMentorshipRequest(Long requestId, Long alumniId) {
        MentorshipRequestEntity mentorshipRequest = mentorshipRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Mentorship Request Not Found"));

        AlumniEntity alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() -> new RuntimeException("Alumni Not Found"));

        mentorshipRequest.setAlumni(alumni);
        mentorshipRequest.setStatus(Status.VERIFIED); 
        return mentorshipRequestRepository.save(mentorshipRequest);
    }

    public void deleteMentorshipRequestById(Long id) {
        MentorshipRequestEntity mentorshipRequestEntity = mentorshipRequestRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        mentorshipRequestRepository.delete(mentorshipRequestEntity);
    }
}
