package com.bob.JobCreation.service;

import com.bob.db.dto.JobPositionsDTO;
import com.bob.db.entity.JobApplicationFeeEntity;
import com.bob.db.repository.JobApplicationFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobApplicationFeeService {


    // Model
    //  private Integer job_application_fee_id; --> default
    //  private UUID position_id; -->set
    //  private Integer application_fee_id; -->set

    //repo
    @Autowired
    private JobApplicationFeeRepository jobApplicationFeeRepository;

    private JobApplicationFeeEntity setValues(JobPositionsDTO jobPositionsDTO, UUID position_id){
        JobApplicationFeeEntity jobApplicationFee = new JobApplicationFeeEntity();

        jobApplicationFee.setPositionId(position_id);
        jobApplicationFee.setApplicationFeeId(jobApplicationFee.getApplicationFeeId());

        return jobApplicationFee;
    }

    public JobApplicationFeeEntity createApplicationFee(JobPositionsDTO jobPositionsDTO, UUID position_id){
        JobApplicationFeeEntity jobApplicationFee = setValues(jobPositionsDTO,position_id);
        return jobApplicationFeeRepository.save(jobApplicationFee);
    }

    public JobApplicationFeeEntity getByPositionIdApplicationFee(UUID position_id){
        return jobApplicationFeeRepository.findByPositionId(position_id);
    }

}