package com.virtualgiving.repositories;

import com.virtualgiving.entities.MentorshipOpportunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorshipOpportunityRepository extends JpaRepository<MentorshipOpportunityEntity, Long> {
    
}
