package com.example.MasterData.Repository;

import com.example.MasterData.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "SELECT dd.country_id,dd.country_name FROM country dd", nativeQuery = true)
    List<Map<Long,String>> getData();
}
