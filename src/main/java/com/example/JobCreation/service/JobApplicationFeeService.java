package com.example.JobCreation.service;

import com.example.JobCreation.dto.JobPositionsDTO;
import com.example.JobCreation.model.JobApplicationFee;
import com.example.JobCreation.repository.JobApplicationFeeRepository;
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

    private JobApplicationFee setValues(JobPositionsDTO jobPositionsDTO,UUID position_id){
        JobApplicationFee jobApplicationFee = new JobApplicationFee();

        jobApplicationFee.setPosition_id(position_id);
        jobApplicationFee.setApplication_fee_id(jobApplicationFee.getApplication_fee_id());

        return jobApplicationFee;
    }

    public JobApplicationFee createApplicationFee(JobPositionsDTO jobPositionsDTO,UUID position_id){
        JobApplicationFee jobApplicationFee = setValues(jobPositionsDTO,position_id);
        return jobApplicationFeeRepository.save(jobApplicationFee);
    }

    public JobApplicationFee getByPositionIdApplicationFee(UUID position_id){
        return jobApplicationFeeRepository.findByPositionId(position_id);
    }

}
