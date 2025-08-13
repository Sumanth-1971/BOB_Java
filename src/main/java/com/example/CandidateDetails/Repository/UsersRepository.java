package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.Interviews;
import com.example.CandidateDetails.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT * FROM users WHERE role = :role", nativeQuery = true)
    List<Users> findByRole(@Param("role") String role);
}
