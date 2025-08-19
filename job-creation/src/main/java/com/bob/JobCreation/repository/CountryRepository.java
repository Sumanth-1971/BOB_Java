package com.bob.JobCreation.repository;

import com.bob.JobCreation.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    // Additional query methods can be defined here if needed

}