package com.bob.CandidateDetails.Repository;

import com.bob.CandidateDetails.Model.Templates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Templates,Integer> {
}
