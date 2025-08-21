package com.bob.db.repository;

import com.bob.db.entity.CandidateNationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateNationalityRepository extends JpaRepository<CandidateNationality,Integer> {
}
