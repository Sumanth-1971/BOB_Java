package com.bob.db.repository;

import com.bob.db.entity.LocationEntity;
import com.bob.db.dto.LocationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity,Long> {
    @Query(value = "SELECT dd.location_id,dd.location_name,dd.city_id FROM locations dd", nativeQuery = true)
    List<LocationDto> getData();

    @Query(value = "SELECT * FROM locations WHERE  city_id= :cityId", nativeQuery = true)
    List<LocationEntity> findByCityId(@Param("cityId") Long cityId);

    @Query(value = "SELECT city_id j FROM locations j WHERE  j.location_id= :locationId", nativeQuery = true)
    Long findCityIdByLocationId(@Param("locationId") Long locationId);

    @Query("SELECT l.cityId FROM LocationEntity l WHERE l.locationId = ?1")
    Long findcityIdByLocationId(Long locationId);
}
