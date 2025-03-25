package com.virtualgiving.repositories;

import com.virtualgiving.entities.InternshipOpportunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipOpportunityRepository extends JpaRepository<InternshipOpportunityEntity, Long> {

}
