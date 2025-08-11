package com.example.JobCreation.repository;

import com.example.JobCreation.model.JobAgeRelaxations;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface JobAgeRelaxationsRepository extends JpaRepository<JobAgeRelaxations,Integer> {

    @Query(value = "SELECT * FROM job_age_relaxations WHERE position_id = ?1", nativeQuery = true)
    JobAgeRelaxations findByPositionId(UUID positionId);

    @Modifying
    @Transactional
    @Query("UPDATE JobAgeRelaxations j SET j.age_relaxation_id = :ageRelaxationId WHERE j.position_id = :positionId")
    int updateByPositionId(@Param("positionId") UUID positionId, @Param("ageRelaxationId") int ageRelaxationId);
}
