package com.bob.db.repository;

import com.bob.db.entity.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidates, UUID> {
    @Query("SELECT c FROM Candidates c WHERE c.email = ?1")
    Optional<Candidates> findByEmail(String email);
}
