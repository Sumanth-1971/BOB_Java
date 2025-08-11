package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.Interviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviews, Integer> {


}
