package com.virtualgiving.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virtualgiving.entities.AlumniEntity;

@Repository
public interface AlumniRepository extends JpaRepository<AlumniEntity, Long> {

}