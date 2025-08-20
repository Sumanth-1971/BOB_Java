package com.example.MasterData.Repository;

import com.example.MasterData.entity.CandidatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidatesRepository extends JpaRepository<CandidatesEntity, UUID> {
    List<CandidatesEntity> findByCandidateIdIn(List<UUID> candidateIds);
}
