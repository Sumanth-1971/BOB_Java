package com.bob.db.repository;

import com.bob.db.entity.City;
import com.bob.db.dto.Citydto;
import com.bob.db.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    @Query(value = "SELECT dd.city_id,dd.city_name,dd.state_id FROM city dd", nativeQuery = true)
    List<Citydto> getData();

    @Query(value = "SELECT * FROM city WHERE location_id = :locationId", nativeQuery = true)
    List<State> findByPositionId(@Param("locationId") Integer locationId);

    @Query(value = "SELECT state_id j FROM locations j WHERE  j.state_id= :stateId", nativeQuery = true)
    Long findStateIdByStateId(@Param("stateId") Long stateId);

    @Query("SELECT c.state_id FROM City c WHERE c.city_id = ?1")
    Long findStateIdByCityId(Long cityId);
}
