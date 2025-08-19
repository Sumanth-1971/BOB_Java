package com.example.MasterData.Repository;

import com.example.MasterData.Model.SpecialCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SpecialCategoriesRepository extends JpaRepository<SpecialCategories,Long> {

}
