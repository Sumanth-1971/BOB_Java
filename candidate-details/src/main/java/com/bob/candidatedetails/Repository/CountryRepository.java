package com.bob.candidatedetails.Repository;

import com.bob.candidatedetails.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {


    @Query(value = "SELECT * FROM country WHERE state_id = :stateId", nativeQuery = true)
    List<Country> findByCityId(@Param("stateId") Integer stateId);
}
