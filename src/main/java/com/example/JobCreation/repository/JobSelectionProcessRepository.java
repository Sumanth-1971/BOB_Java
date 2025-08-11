package com.example.JobCreation.repository;

import com.example.JobCreation.model.JobSelectionProcess;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JobSelectionProcessRepository extends JpaRepository<JobSelectionProcess,Integer> {
    @Query(value = "SELECT * FROM job_selection_process WHERE position_id = ?1", nativeQuery = true)
    JobSelectionProcess findByPositionId(UUID positionId);

    //update by position id
    @Modifying
    @Transactional
    @Query(value = "UPDATE job_selection_process SET selection_procedure = ?2 WHERE position_id = ?1", nativeQuery = true)
    int updateByPositionId(UUID positionId, String selectionProcess);
}
