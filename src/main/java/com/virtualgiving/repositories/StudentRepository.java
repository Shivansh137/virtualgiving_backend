package com.virtualgiving.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualgiving.entities.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByEmail(String username);

}
