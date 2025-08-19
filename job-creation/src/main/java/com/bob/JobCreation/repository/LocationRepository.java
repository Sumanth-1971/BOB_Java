package com.bob.JobCreation.repository;

import com.bob.JobCreation.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l.city_id FROM Location l WHERE l.location_id = ?1")
    Long findcityIdByLocationId(Long locationId);
}