package com.bob.db.repository;

import com.bob.db.entity.JobApplicationFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JobApplicationFeeRepository extends JpaRepository<JobApplicationFeeEntity,Integer> {

    // Custom query to find JobApplicationFee by positionId
    @Query(value = "SELECT * FROM job_application_fee WHERE position_id = ?1", nativeQuery = true)
    JobApplicationFeeEntity findByPositionId(UUID positionId);


//    @Modifying
//    @Transactional
//    @Query("UPDATE JobApplicationFee j SET j.application_fee_id = :applicationFeeId WHERE j.position_id = :positionId")
//    int updateByPositionId(@Param("positionId") UUID positionId,
//                           @Param("applicationFeeId") int applicationFeeId);

}
