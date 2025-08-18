package com.example.JobCreation.repository;

import com.example.JobCreation.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {



}