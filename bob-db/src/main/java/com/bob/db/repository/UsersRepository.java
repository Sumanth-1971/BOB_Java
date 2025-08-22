package com.bob.db.repository;

import com.bob.db.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT * FROM users WHERE role = :role", nativeQuery = true)
    List<Users> findByRole(@Param("role") String role);
}
