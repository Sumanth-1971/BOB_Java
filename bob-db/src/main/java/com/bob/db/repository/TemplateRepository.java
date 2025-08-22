package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.Templates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Templates,Integer> {
}
