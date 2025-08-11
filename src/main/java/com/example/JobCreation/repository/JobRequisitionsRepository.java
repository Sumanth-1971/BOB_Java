package com.example.JobCreation.repository;

import com.example.JobCreation.model.JobRequisitions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobRequisitionsRepository extends JpaRepository<JobRequisitions, UUID> {

    @Query(value = "SELECT * FROM job_requisitions WHERE requisition_status = :status", nativeQuery = true)
    List<JobRequisitions> findByRequisitionStatus(@Param("status") String status);


}
