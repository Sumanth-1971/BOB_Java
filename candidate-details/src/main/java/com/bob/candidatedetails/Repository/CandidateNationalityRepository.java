package com.bob.candidatedetails.Repository;

import com.bob.candidatedetails.Model.CandidateNationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateNationalityRepository extends JpaRepository<CandidateNationality,Integer> {
}
