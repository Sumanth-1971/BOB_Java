package com.bob.JobCreation.service;

import com.bob.JobCreation.dto.JobPositionsDTO;
import com.bob.JobCreation.model.JobAgeRelaxations;
import com.bob.JobCreation.repository.JobAgeRelaxationsRepository;

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

    private JobAgeRelaxations setValues(JobPositionsDTO jobPositionsDTO, UUID position_id){
        JobAgeRelaxations jobAgeRelaxations = new JobAgeRelaxations();
        jobAgeRelaxations.setPosition_id(position_id);
        jobAgeRelaxations.setAge_relaxation_id( 1 );
        return jobAgeRelaxations;

    }

    @Transactional
    public JobAgeRelaxations createAgeRelaxation(JobPositionsDTO jobPositionsDTO, UUID position_id){
        JobAgeRelaxations jobAgeRelaxations = setValues(jobPositionsDTO,position_id);
        return jobAgeRelaxationsRepository.save(jobAgeRelaxations);
    }

    @Transactional
    public JobAgeRelaxations getByPositionIdAgeRelaxation(UUID position_id){
        return jobAgeRelaxationsRepository.findByPositionId(position_id);
    }
}