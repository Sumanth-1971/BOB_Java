package com.bob.db.repository;

import com.bob.db.entity.Location;
import com.bob.db.dto.Locationdto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    @Query(value = "SELECT dd.location_id,dd.location_name,dd.city_id FROM locations dd", nativeQuery = true)
    List<Locationdto> getData();

    @Query(value = "SELECT * FROM locations WHERE  city_id= :cityId", nativeQuery = true)
    List<Location> findByCityId(@Param("cityId") Long cityId);

    @Query(value = "SELECT city_id j FROM locations j WHERE  j.location_id= :locationId", nativeQuery = true)
    Long findCityIdByLocationId(@Param("locationId") Long locationId);

    @Query("SELECT l.city_id FROM Location l WHERE l.location_id = ?1")
    Long findcityIdByLocationId(Long locationId);
}
