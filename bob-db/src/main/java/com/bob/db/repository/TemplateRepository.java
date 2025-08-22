package com.bob.db.repository;

import com.bob.db.entity.Templates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Templates,Integer> {
}
