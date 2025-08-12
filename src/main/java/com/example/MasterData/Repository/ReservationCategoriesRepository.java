package com.example.MasterData.Repository;

import com.example.MasterData.Model.ReservationCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationCategoriesRepository extends JpaRepository<ReservationCategories,Long> {
}
