package com.example.MasterData.Repository;

import com.example.MasterData.entity.PositionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PositionsRepository extends JpaRepository<PositionsEntity, UUID> {
    List<PositionsEntity> findByPositionIdIn(List<UUID> positionIds);
}
