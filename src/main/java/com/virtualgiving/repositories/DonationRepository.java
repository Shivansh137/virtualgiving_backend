package com.virtualgiving.repositories;

import com.virtualgiving.entities.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<DonationEntity, Long> {
}
