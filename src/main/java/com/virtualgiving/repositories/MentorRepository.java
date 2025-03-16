package com.virtualgiving.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virtualgiving.entities.MentorEntity;

@Repository
public interface MentorRepository extends JpaRepository<MentorEntity, Long> {

}