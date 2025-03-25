package com.virtualgiving.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtualgiving.entities.MentorshipRequestEntity;

public interface MentorshipRequestRepository extends JpaRepository<MentorshipRequestEntity, Long> {

}
