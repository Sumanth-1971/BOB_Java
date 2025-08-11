package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.JobPostingLocation;
import com.example.CandidateDetails.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobPostingLocationRepository extends JpaRepository<JobPostingLocation, Long> {

    @Query(value = "SELECT * FROM job_posting_location WHERE position_id = :positionId", nativeQuery = true)
    List<JobPostingLocation> findByPositionId(@Param("positionId") UUID positionId);
}
