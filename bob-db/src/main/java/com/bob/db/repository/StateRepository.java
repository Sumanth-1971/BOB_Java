package com.bob.db.repository;

import com.bob.db.entity.StateEntity;
import com.bob.db.dto.StateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<StateEntity,Long> {
    @Query(value = "SELECT dd.state_id,dd.state_name,dd.country_id FROM state dd", nativeQuery = true)
    List<StateDto> getData();

    @Query(value = "SELECT * FROM state WHERE city_id = :cityId", nativeQuery = true)
    List<StateEntity> findByCityId(@Param("cityId") Integer cityId);


    @Query(value = "SELECT country_id j FROM state j WHERE  j.state_id= :stateId", nativeQuery = true)
    Long findCountryIdByStateId(@Param("stateId") Long stateId);
	
//	 @Query("SELECT s.country_id FROM State s WHERE s.state_id = ?1")
//    Long findCountryIdByStateId(Long stateId);
}
