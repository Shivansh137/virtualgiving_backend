package com.virtualgiving.repositories;

import com.virtualgiving.entities.BankDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailsRepository extends JpaRepository<BankDetailsEntity, Long> {
}
