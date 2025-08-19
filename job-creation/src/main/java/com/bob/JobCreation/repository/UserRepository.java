package com.bob.JobCreation.repository;

import com.bob.JobCreation.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {



}