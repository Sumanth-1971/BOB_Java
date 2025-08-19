package com.example.JobCreation.repository;

import com.example.JobCreation.model.AuditTrailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrailEntity, UUID> {
    // Add custom query methods if needed
}