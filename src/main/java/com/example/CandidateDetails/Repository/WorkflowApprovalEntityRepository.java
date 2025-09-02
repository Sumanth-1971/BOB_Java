package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.entity.WorkflowApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface WorkflowApprovalEntityRepository extends JpaRepository<WorkflowApprovalEntity, UUID> {
    List<WorkflowApprovalEntity> findByEntityId(UUID applicationId);
    // Add custom query methods if needed
}
