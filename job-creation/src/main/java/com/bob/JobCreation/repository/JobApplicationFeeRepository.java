package com.bob.JobCreation.repository;

import com.bob.JobCreation.model.JobApplicationFee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface JobApplicationFeeRepository extends JpaRepository<JobApplicationFee,Integer> {

    // Custom query to find JobApplicationFee by positionId
    @Query(value = "SELECT * FROM job_application_fee WHERE position_id = ?1", nativeQuery = true)
    JobApplicationFee findByPositionId(UUID positionId);


    @Modifying
    @Transactional
    @Query("UPDATE JobApplicationFee j SET j.application_fee_id = :applicationFeeId WHERE j.position_id = :positionId")
    int updateByPositionId(@Param("positionId") UUID positionId,
                           @Param("applicationFeeId") int applicationFeeId);
}
