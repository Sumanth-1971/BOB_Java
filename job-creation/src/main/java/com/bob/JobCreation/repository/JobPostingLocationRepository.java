package com.bob.JobCreation.repository;

import com.bob.JobCreation.model.JobPostingLocation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface JobPostingLocationRepository  extends JpaRepository<JobPostingLocation,Integer> {


    @Query(value = "SELECT * FROM job_posting_location WHERE position_id = ?1", nativeQuery = true)
    JobPostingLocation findByPositionId(UUID positionId);

    @Modifying
    @Transactional
    @Query("UPDATE JobPostingLocation j SET j.dept_id = :deptId, j.location_id = :locationId WHERE j.position_id = :positionId")
    int updateByPositionId(@Param("positionId") UUID positionId,
                           @Param("deptId") int deptId,
                           @Param("locationId") int locationId);


}
