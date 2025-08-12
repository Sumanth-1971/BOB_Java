package com.example.MasterData.Repository;

import com.example.MasterData.Model.City;
import com.example.MasterData.dto.Citydto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    @Query(value = "SELECT dd.city_id,dd.city_name,dd.state_id FROM city dd", nativeQuery = true)
    List<Citydto> getData();
}
