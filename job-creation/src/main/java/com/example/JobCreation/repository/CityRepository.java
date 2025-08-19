package com.example.JobCreation.repository;

import com.example.JobCreation.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c.state_id FROM City c WHERE c.city_id = ?1")
    Long findStateIdByCityId(Long cityId);
}