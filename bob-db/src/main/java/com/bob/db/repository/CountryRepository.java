package com.bob.db.repository;

import com.bob.db.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "SELECT dd.country_id,dd.country_name FROM country dd", nativeQuery = true)
    List<Map<Long,String>> getData();


    @Query(value = "SELECT * FROM country WHERE state_id = :stateId", nativeQuery = true)
    List<Country> findByCityId(@Param("stateId") Integer stateId);
}
