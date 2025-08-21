package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.Interviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InterviewsRepository extends JpaRepository<Interviews, Integer> {

    @Query(value = "SELECT * FROM interviews WHERE candidate_id = :candidateId AND position_id = :positionId", nativeQuery = true)
    List<Interviews> findByCandidateIdAndPositionIdNative(@Param("candidateId") UUID candidateId, @Param("positionId") UUID positionId);

    @Query(value = "SELECT * FROM interviews WHERE scheduled_at between :startDate AND :endDate", nativeQuery = true)
    List<Interviews> findByScheduledAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
