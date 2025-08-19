package com.bob.JobCreation.repository;

import com.bob.JobCreation.model.Positions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobPositionsRepository extends JpaRepository<Positions, UUID> {
    @Query(value = "SELECT * FROM positions WHERE requisition_id = :requisitionId", nativeQuery = true)
    List<Positions> findAllByRequisitionId(@Param("requisitionId") UUID requisitionId);


}
