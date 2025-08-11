package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.Country;
import com.example.CandidateDetails.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {


    @Query(value = "SELECT * FROM country WHERE state_id = :stateId", nativeQuery = true)
    List<Country> findByCityId(@Param("stateId") Integer stateId);
}
