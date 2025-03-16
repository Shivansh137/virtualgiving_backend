package com.virtualgiving.controllers;

import com.virtualgiving.dto.LoginRequest;
import com.virtualgiving.entities.MentorEntity;
import com.virtualgiving.services.MentorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mentors")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @PostMapping("/register")
    public MentorEntity registerMentor(@RequestBody MentorEntity mentor) {
        return mentorService.registerMentor(mentor);
    }

    @GetMapping
    public List<MentorEntity> getAllMentors() {
        return mentorService.getAllMentors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorEntity> getMentorById(@PathVariable int id) {
        Optional<MentorEntity> mentor = mentorService.getMentorById(id);
        return mentor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MentorEntity> updateMentor(@PathVariable int id, @RequestBody MentorEntity updatedMentor) {
        MentorEntity mentor = mentorService.updateMentor(id, updatedMentor);
        return (mentor != null) ? ResponseEntity.ok(mentor) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMentor(@PathVariable int id) {
        boolean isDeleted = mentorService.deleteMentor(id);
        return isDeleted ? ResponseEntity.ok("Mentor deleted successfully") : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<MentorEntity> loginMentor(@RequestBody LoginRequest loginRequest) {
    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    MentorEntity mentor = mentorService.loginMentor(email, password);
    return (mentor != null) ? ResponseEntity.ok(mentor) : ResponseEntity.status(401).build();
    }
}
