package com.bob.db.repository;

import com.bob.db.entity.JobAgeRelaxationsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JobAgeRelaxationsRepository extends JpaRepository<JobAgeRelaxationsEntity,Integer> {

    @Transactional
    @Query(value = "SELECT * FROM job_age_relaxations WHERE position_id = ?1", nativeQuery = true)
    JobAgeRelaxationsEntity findByPositionId(UUID positionId);

}