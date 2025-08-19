package com.bob.JobCreation.repository;

import com.bob.JobCreation.model.WorkflowApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkflowApprovalEntityRepository extends JpaRepository<WorkflowApprovalEntity, UUID> {
    // Add custom query methods if needed
}