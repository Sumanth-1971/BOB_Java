package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.City;
import com.example.CandidateDetails.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    @Query(value = "SELECT * FROM city WHERE location_id = :locationId", nativeQuery = true)
    List<State> findByPositionId(@Param("locationId") Integer locationId);

    @Query(value = "SELECT state_id j FROM locations j WHERE  j.state_id= :stateId", nativeQuery = true)
    Long findStateIdByStateId(@Param("stateId") Long stateId);
}
