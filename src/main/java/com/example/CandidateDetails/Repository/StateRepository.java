package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.CandidateApplications;
import com.example.CandidateDetails.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query(value = "SELECT * FROM state WHERE city_id = :cityId", nativeQuery = true)
    List<State> findByCityId(@Param("cityId") Integer cityId);


    @Query(value = "SELECT country_id j FROM state j WHERE  j.state_id= :stateId", nativeQuery = true)
    Long findCountryIdByStateId(@Param("stateId") Long stateId);
}
