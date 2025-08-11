package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.CandidateNationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateNationalityRepository extends JpaRepository<CandidateNationality,Integer> {
}
