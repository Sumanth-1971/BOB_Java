package com.bob.db.repository;

import com.bob.db.entity.State;
import com.bob.db.dto.StateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {
    @Query(value = "SELECT dd.state_id,dd.state_name,dd.country_id FROM state dd", nativeQuery = true)
    List<StateDto> getData();
}
