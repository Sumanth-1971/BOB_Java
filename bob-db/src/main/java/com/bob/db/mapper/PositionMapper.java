package com.bob.db.mapper;

import com.bob.db.dto.JobPositionsDTO;
import com.bob.db.entity.JobPostingLocationEntity;
import com.bob.db.entity.JobSelectionProcessEntity;
import com.bob.db.entity.JobVacanciesEntity;
import com.bob.db.entity.PositionsEntity;

public class PositionMapper {

    public static JobPositionsDTO positionsToDTO(PositionsEntity positions,
                                                 JobPostingLocationEntity jobPostingLocation,
                                                 JobVacanciesEntity jobVacancies,
                                                 JobSelectionProcessEntity jobSelectionProcess) {
        JobPositionsDTO positionsDTO = new JobPositionsDTO();
        positionsDTO.setPosition_id(positions.getPositionId());
        positionsDTO.setRequisition_id(positions.getRequisitionId());
        positionsDTO.setPosition_title(positions.getPositionTitle());
        positionsDTO.setDescription(positions.getDescription());
        positionsDTO.setRoles_responsibilities(positions.getRolesResponsibilities());
        positionsDTO.setGrade_id(positions.getGradeId());
        positionsDTO.setEmployment_type(positions.getEmploymentType());
        positionsDTO.setEligibility_age_min(positions.getEligibilityAgeMin());
        positionsDTO.setEligibility_age_max(positions.getEligibilityAgeMax());
        positionsDTO.setMandatory_qualification(positions.getMandatoryQualification());
        positionsDTO.setPreferred_qualification(positions.getPreferredQualification());
        positionsDTO.setMandatory_experience(positions.getMandatoryExperience());
        positionsDTO.setPreferred_experience(positions.getPreferredExperience());
        positionsDTO.setProbation_period(positions.getProbationPeriod());
        positionsDTO.setDocuments_required(positions.getDocumentsRequired());
        positionsDTO.setMin_credit_score(positions.getMinCreditScore());
        return positionsDTO;
    }



}
