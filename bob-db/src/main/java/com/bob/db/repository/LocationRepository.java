package com.bob.db.repository;

import com.bob.db.entity.Location;
import com.bob.db.dto.Locationdto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    @Query(value = "SELECT dd.location_id,dd.location_name,dd.city_id FROM locations dd", nativeQuery = true)
    List<Locationdto> getData();
}
