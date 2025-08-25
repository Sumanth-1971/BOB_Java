package com.bob.JobCreation.service;

import com.bob.db.dto.JobPositionsDTO;
import com.bob.db.entity.JobAgeRelaxationsEntity;
import com.bob.db.repository.JobAgeRelaxationsRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobAgeRelaxationService {



    // Model data
    // private Integer job_relaxation_id; --> Default

    // setThis values
    // private UUID position_id; -->Set
    // private Integer age_relaxation_id; -->set

    //Repo
    @Autowired
    private JobAgeRelaxationsRepository jobAgeRelaxationsRepository;

    private JobAgeRelaxationsEntity setValues(JobPositionsDTO jobPositionsDTO, UUID position_id){
        JobAgeRelaxationsEntity jobAgeRelaxations = new JobAgeRelaxationsEntity();
        jobAgeRelaxations.setPositionId(position_id);
        jobAgeRelaxations.setAgeRelaxationId( 1 );
        return jobAgeRelaxations;

    }

    @Transactional
    public JobAgeRelaxationsEntity createAgeRelaxation(JobPositionsDTO jobPositionsDTO, UUID position_id){
        JobAgeRelaxationsEntity jobAgeRelaxations = setValues(jobPositionsDTO,position_id);
        return jobAgeRelaxationsRepository.save(jobAgeRelaxations);
    }

    @Transactional
    public JobAgeRelaxationsEntity getByPositionIdAgeRelaxation(UUID position_id){
        return jobAgeRelaxationsRepository.findByPositionId(position_id);
    }
}