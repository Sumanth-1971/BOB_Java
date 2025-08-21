package com.bob.db.repository;

import com.bob.db.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {

    @Query(value = "SELECT d.skill_id,d.skill_desc FROM skill d", nativeQuery = true)
    List<Map<Long,String>> getSkillIdDescriptions();
}
