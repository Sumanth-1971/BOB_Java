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


    @Query("SELECT c.stateId FROM CityEntity c WHERE c.cityId = ?1")
    Long findStateIdByCityId(Long cityId);

}
