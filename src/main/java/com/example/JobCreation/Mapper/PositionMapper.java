package com.example.JobCreation.Mapper;

import com.example.JobCreation.dto.JobPositionsDTO;
import com.example.JobCreation.model.JobPostingLocation;
import com.example.JobCreation.model.JobSelectionProcess;
import com.example.JobCreation.model.JobVacancies;
import com.example.JobCreation.model.Positions;

public class PositionMapper {

    public static JobPositionsDTO positionsToDTO(Positions positions,
                                                 JobPostingLocation jobPostingLocation,
                                                 JobVacancies jobVacancies,
                                                 JobSelectionProcess jobSelectionProcess) {
        JobPositionsDTO positionsDTO = new JobPositionsDTO();
        positionsDTO.setPosition_id(positions.getPosition_id());
        positionsDTO.setRequisition_id(positions.getRequisition_id());
        positionsDTO.setPosition_title(positions.getPosition_title());
        positionsDTO.setDescription(positions.getDescription());
        positionsDTO.setRoles_responsibilities(positions.getRoles_responsibilities());
        positionsDTO.setGrade_id(positions.getGrade_id());
        positionsDTO.setEmployment_type(positions.getEmployment_type());
        positionsDTO.setEligibility_age_min(positions.getEligibility_age_min());
        positionsDTO.setEligibility_age_max(positions.getEligibility_age_max());
        positionsDTO.setMandatory_qualification(positions.getMandatory_qualification());
        positionsDTO.setPreferred_qualification(positions.getPreferred_qualification());
        positionsDTO.setMandatory_experience(positions.getMandatory_experience());
        positionsDTO.setPreferred_experience(positions.getPreferred_experience());
        positionsDTO.setProbation_period(positions.getProbation_period());
        positionsDTO.setDocuments_required(positions.getDocuments_required());
        positionsDTO.setMin_credit_score(positions.getMin_credit_score());
        return positionsDTO;
    }



}
