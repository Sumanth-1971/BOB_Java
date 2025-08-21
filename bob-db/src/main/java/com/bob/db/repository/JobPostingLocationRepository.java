package com.bob.db.repository;

import com.bob.db.entity.JobPostingLocation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobPostingLocationRepository extends JpaRepository<JobPostingLocation, Long> {

    @Query(value = "SELECT jl.location_id FROM job_posting_location jl WHERE jl.position_id = :positionId", nativeQuery = true)
    List<Long> findByPositionId(@Param("positionId") UUID positionId);

    @Query(value = "SELECT * FROM job_posting_location WHERE position_id = ?1", nativeQuery = true)
    JobPostingLocation findByPositionId2(UUID positionId);

    @Modifying
    @Transactional
    @Query("UPDATE JobPostingLocation j SET j.dept_id = :deptId, j.location_id = :locationId WHERE j.position_id = :positionId")
    int updateByPositionId(@Param("positionId") UUID positionId,
                           @Param("deptId") int deptId,
                           @Param("locationId") int locationId);

}
