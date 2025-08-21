package com.bob.db.repository;

import com.bob.db.entity.SpecialCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialCategoriesRepository extends JpaRepository<SpecialCategories,Long> {

}
