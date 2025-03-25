package com.virtualgiving.controllers;

import com.virtualgiving.entities.MentorshipRequestEntity;
import com.virtualgiving.services.MentorshipRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentorship_requests")
public class MentorshipRequestController {
    private final MentorshipRequestService mentorshipRequestService;

    public MentorshipRequestController(MentorshipRequestService mentorshipRequestService) {
        this.mentorshipRequestService = mentorshipRequestService;
    }

    @GetMapping
    public List<MentorshipRequestEntity> getAllMentorshipRequests() {
        return mentorshipRequestService.getAllMentorshipRequests();
    }

    @GetMapping("/{id}")
    public MentorshipRequestEntity getMentorshipRequestById(@PathVariable Long id) {
        return mentorshipRequestService.getMentorshipRequestById(id);
    }

    @PostMapping
    public MentorshipRequestEntity addNewMentorshipRequest(@Valid @RequestBody MentorshipRequestEntity mentorshipRequestEntity) {
        return mentorshipRequestService.addNewMentorshipRequest(mentorshipRequestEntity);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<String> acceptMentorshipRequest(@PathVariable Long id, @RequestParam Long alumniId) {
        mentorshipRequestService.acceptMentorshipRequest(id, alumniId);
        return ResponseEntity.ok("Mentorship request accepted successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMentorshipRequestById(@PathVariable Long id) {
        mentorshipRequestService.deleteMentorshipRequestById(id);
        return ResponseEntity.ok("Mentorship request deleted successfully");
    }
}
