package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.CandidateApplications;
import com.example.CandidateDetails.Model.Interviews;
import com.example.CandidateDetails.Model.JobPostingLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviews, Integer> {
//    @Query(value = "SELECT * FROM interviews WHERE candidate_id = ?1 AND position_id = ?2", nativeQuery = true)
//    Interviews findByCandidateIdAndPositionId(UUID candidateId, UUID positionId);
//
//
//    Optional<Interviews> findByPositionIdAndCandidateId2(UUID positionId, UUID candidateId);

}
