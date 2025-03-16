package com.virtualgiving.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtualgiving.entities.OrganizationEntity;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

}
