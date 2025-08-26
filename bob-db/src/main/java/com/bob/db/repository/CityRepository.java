package com.bob.db.repository;

import com.bob.db.entity.CityEntity;
import com.bob.db.dto.CityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity,Long> {

//    @Query(value = "SELECT dd.city_id,dd.city_name,dd.state_id FROM city dd", nativeQuery = true)
//    List<CityDto> getData();

//    @Query(value = "SELECT * FROM city WHERE location_id = :locationId", nativeQuery = true)
//    List<State> findByPositionId(@Param("locationId") Integer locationId);


//    @Query("SELECT c.state_id FROM CityEntity c WHERE c.city_id = ?1")
//    Long findStateIdByCityId(Long cityId);

    @Query("SELECT c.stateId FROM CityEntity c WHERE c.cityId = ?1")
    Long findStateIdByCityId(Long cityId);

}
