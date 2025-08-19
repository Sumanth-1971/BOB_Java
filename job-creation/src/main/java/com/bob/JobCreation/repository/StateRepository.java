package com.bob.JobCreation.repository;

import com.bob.JobCreation.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    // Additional query methods can be defined here if needed

    @Query("SELECT s.country_id FROM State s WHERE s.state_id = ?1")
    Long findCountryIdByStateId(Long stateId);

}