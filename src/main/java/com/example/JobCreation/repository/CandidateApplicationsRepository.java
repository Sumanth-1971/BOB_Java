package com.example.JobCreation.repository;

import com.example.JobCreation.model.CandidateApplicationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CandidateApplicationsRepository extends JpaRepository<CandidateApplicationsEntity, UUID> {

    List<CandidateApplicationsEntity> findByPositionId(UUID positionId);

    List<CandidateApplicationsEntity> findByCandidateId(UUID candidateId);

    List<CandidateApplicationsEntity> findByCandidateIdAndPositionId(UUID candidateId, UUID positionId);

    List<CandidateApplicationsEntity> findByApplicationStatus(String status);


//    List<CandidateApplicationsEntity> findByPositionId(List<UUID> positionId);
}

