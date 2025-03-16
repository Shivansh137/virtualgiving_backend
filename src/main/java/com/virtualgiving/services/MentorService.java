package com.virtualgiving.services;

import com.virtualgiving.entities.MentorEntity;
import com.virtualgiving.repositories.MentorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MentorService {

    @Autowired
    private MentorRepository mentorRepository;

    public MentorEntity registerMentor(MentorEntity mentor) {
        return mentorRepository.save(mentor);
    }

    public List<MentorEntity> getAllMentors() {
        return mentorRepository.findAll();
    }

    public Optional<MentorEntity> getMentorById(int id) {
        return mentorRepository.findById((long) id);
    }

    public MentorEntity updateMentor(int id, MentorEntity updatedMentor) {
        Optional<MentorEntity> existingMentor = mentorRepository.findById((long) id);
        if (existingMentor.isPresent()) {
            MentorEntity mentor = existingMentor.get();
            mentor.setName(updatedMentor.getName());
            mentor.setEmail(updatedMentor.getEmail());
            mentor.setContact_numbers(updatedMentor.getContact_numbers());
            mentor.setRole(updatedMentor.getRole());
            mentor.setProfile_picture(updatedMentor.getProfile_picture());
            mentor.setPassword(updatedMentor.getPassword());
            mentor.setStreet(updatedMentor.getStreet());
            mentor.setCity(updatedMentor.getCity());
            mentor.setState(updatedMentor.getState());
            mentor.setMentor(updatedMentor.isMentor());
            return mentorRepository.save(mentor);
        }
        return null;
    }

    public boolean deleteMentor(int id) {
        if (mentorRepository.existsById((long) id)) {
            mentorRepository.deleteById((long) id);
            return true;
        }
        return false;
    }

    public MentorEntity loginMentor(String email, String password) {
        List<MentorEntity> mentors = mentorRepository.findAll();
        for (MentorEntity mentor : mentors) {
            if (mentor.getEmail().equals(email) && mentor.getPassword().equals(password)) {
                return mentor; // Login successful
            }
        }
        return null; // Login failed
    }
}