package com.bob.candidatedetails.Repository;

import com.bob.candidatedetails.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

    @Query(value = "SELECT * FROM locations WHERE  city_id= :cityId", nativeQuery = true)
    List<Location> findByCityId(@Param("cityId") Long cityId);

    @Query(value = "SELECT city_id j FROM locations j WHERE  j.location_id= :locationId", nativeQuery = true)
    Long findCityIdByLocationId(@Param("locationId") Long locationId);


}
