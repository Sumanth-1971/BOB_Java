package com.bob.JobCreation.service;

import com.bob.JobCreation.dto.JobPositionsDTO;
import com.bob.JobCreation.model.JobSelectionProcess;
import com.bob.JobCreation.repository.JobSelectionProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobSelectionProcessService {

    @Autowired
    private JobSelectionProcessRepository jobSelectionProcessRepository;

//    private Integer job_selection_id;  --> default
//    private UUID position_id; --> set
//    private String selection_procedure; --> set

    private JobSelectionProcess setValues(JobPositionsDTO jobPositionsDTO,UUID position_id){
        JobSelectionProcess jobSelectionProcess = new JobSelectionProcess();

        jobSelectionProcess.setSelection_procedure(jobPositionsDTO.getSelection_procedure());
        jobSelectionProcess.setPosition_id(position_id);
        return jobSelectionProcess;
    }

    public JobSelectionProcess createSelectionProcess(JobPositionsDTO jobPositionsDTO,UUID position_id){

        JobSelectionProcess jobSelectionProcess = setValues(jobPositionsDTO,position_id);
        return jobSelectionProcessRepository.save(jobSelectionProcess);
    }

    public JobSelectionProcess getByPositionIdSelectionProcess(UUID position_id){
        return jobSelectionProcessRepository.findByPositionId(position_id);
    }

    //updateSelectionProcess
    public JobSelectionProcess updateSelectionProcess(JobPositionsDTO jobPositionsDTO, UUID position_id) {
        JobSelectionProcess existingProcess = getByPositionIdSelectionProcess(position_id);
        if (existingProcess != null) {
            existingProcess.setSelection_procedure(jobPositionsDTO.getSelection_procedure());
            return jobSelectionProcessRepository.save(existingProcess);
        }
        return null; // or throw an exception if not found
    }


}