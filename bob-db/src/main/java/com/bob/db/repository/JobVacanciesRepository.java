package com.bob.db.repository;

import com.bob.db.entity.JobVacanciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JobVacanciesRepository extends JpaRepository<JobVacanciesEntity,Integer> {
    @Query(value = "SELECT * FROM job_vacancies WHERE position_id = ?1", nativeQuery = true)
    JobVacanciesEntity findByPositionId(UUID positionId);

    // Update by position id
//    @Modifying
//    @Transactional
//    @Query("UPDATE JobVacancies j SET j.special_cat_id = :specialCatId, j.reservation_cat_id = :reservationCatId, j.location_id = :locationId, j.no_of_vacancies = :noOfVacancies WHERE j.position_id = :positionId")
//    int updateByPositionId(@Param("positionId") UUID positionId,
//                           @Param("specialCatId") int specialCatId,
//                           @Param("reservationCatId") int reservationCatId,
//                           @Param("locationId") int locationId,
//                           @Param("noOfVacancies") int noOfVacancies);
}
