package com.bob.JobCreation.service;

import com.bob.db.dto.JobPositionsDTO;
import com.bob.db.entity.JobSelectionProcessEntity;
import com.bob.db.repository.JobSelectionProcessRepository;
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

    private JobSelectionProcessEntity setValues(JobPositionsDTO jobPositionsDTO, UUID position_id){
        JobSelectionProcessEntity jobSelectionProcess = new JobSelectionProcessEntity();

        jobSelectionProcess.setSelectionProcedure(jobPositionsDTO.getSelection_procedure());
        jobSelectionProcess.setPosition_id(position_id);
        return jobSelectionProcess;
    }

    public JobSelectionProcessEntity createSelectionProcess(JobPositionsDTO jobPositionsDTO, UUID position_id){

        JobSelectionProcessEntity jobSelectionProcess = setValues(jobPositionsDTO,position_id);
        return jobSelectionProcessRepository.save(jobSelectionProcess);
    }

    public JobSelectionProcessEntity getByPositionIdSelectionProcess(UUID position_id){
        return jobSelectionProcessRepository.findByPositionId(position_id);
    }

    //updateSelectionProcess
    public JobSelectionProcessEntity updateSelectionProcess(JobPositionsDTO jobPositionsDTO, UUID position_id) {
        JobSelectionProcessEntity existingProcess = getByPositionIdSelectionProcess(position_id);
        if (existingProcess != null) {
            existingProcess.setSelectionProcedure(jobPositionsDTO.getSelection_procedure());
            return jobSelectionProcessRepository.save(existingProcess);
        }
        return null; // or throw an exception if not found
    }


}