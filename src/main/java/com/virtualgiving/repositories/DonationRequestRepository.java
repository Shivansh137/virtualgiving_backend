package com.virtualgiving.repositories;

import com.virtualgiving.entities.DonationRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRequestRepository extends JpaRepository<DonationRequestEntity, Long> {
}
