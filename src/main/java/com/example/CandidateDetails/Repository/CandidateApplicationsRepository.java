package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.CandidateApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CandidateApplicationsRepository extends JpaRepository<CandidateApplications, UUID> {

    @Query(value = "SELECT * FROM candidate_applications WHERE position_id = :positionId", nativeQuery = true)
    List<CandidateApplications> findByPositionId(@Param("positionId") UUID positionId);

    @Query(value = "SELECT * FROM candidate_applications WHERE candidate_id = :candidateId", nativeQuery = true)
    List<CandidateApplications> findByCandidateIdNative(@Param("candidateId") UUID candidateId);

//    @Query(value = "SELECT * FROM candidate_applications WHERE candidate_id = :candidateId AND position_id = :positionId", nativeQuery = true)
//    Optional<CandidateApplications> findByCandidateIdAndPositionId(@Param("candidateId") UUID candidateId, @Param("positionId") UUID positionId);


    @Query(value = "SELECT * FROM candidate_applications WHERE candidate_id = :candidateId AND position_id = :positionId", nativeQuery = true)
    List<CandidateApplications> findByCandidateIdAndPositionId(@Param("candidateId") UUID candidateId, @Param("positionId") UUID positionId);

    @Query(value = "SELECT * FROM candidate_applications WHERE application_status = :status", nativeQuery = true)
    List<CandidateApplications> findByApplicationStatus(String status);


}
