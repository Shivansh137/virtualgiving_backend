package com.virtualgiving.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtualgiving.entities.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
}
