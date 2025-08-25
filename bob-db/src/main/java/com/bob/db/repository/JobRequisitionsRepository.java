package com.bob.db.repository;

import com.bob.db.entity.JobRequisitionsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRequisitionsRepository extends JpaRepository<JobRequisitionsEntity, UUID> {

    @Query(value = "SELECT * FROM job_requisitions WHERE requisition_status = :status", nativeQuery = true)
    List<JobRequisitionsEntity> findByRequisitionStatus(@Param("status") String status);


}
