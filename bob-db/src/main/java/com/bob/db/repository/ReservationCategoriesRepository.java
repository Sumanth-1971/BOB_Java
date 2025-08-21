package com.bob.db.repository;

import com.bob.db.entity.ReservationCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationCategoriesRepository extends JpaRepository<ReservationCategories,Long> {
}
