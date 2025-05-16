package com.virtualgiving.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtualgiving.entities.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

    Optional<OrganizationEntity> findByEmail(String email);

}
