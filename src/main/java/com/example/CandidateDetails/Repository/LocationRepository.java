package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.CandidateApplications;
import com.example.CandidateDetails.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

//    @Query(value = "SELECT * FROM locations WHERE  city_id= :cityId", nativeQuery = true)
//    List<Location> findByCityId(@Param("cityId") Long cityId);
}
