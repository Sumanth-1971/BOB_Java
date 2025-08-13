package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.JobPostingLocation;
import com.example.CandidateDetails.Model.Location;
import com.example.CandidateDetails.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobPostingLocationRepository extends JpaRepository<JobPostingLocation, Long> {

    @Query(value = "SELECT jl.location_id FROM job_posting_location jl WHERE jl.position_id = :positionId", nativeQuery = true)
    List<Long> findByPositionId(@Param("positionId") UUID positionId);


}
