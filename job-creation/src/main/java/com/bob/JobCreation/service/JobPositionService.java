package com.bob.JobCreation.service;

import com.bob.db.dto.JobPositionsDTO;
import com.bob.db.dto.ResponseDTO;
import com.bob.db.entity.*;
import com.bob.db.repository.JobPositionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JobPositionService{
    // new repo
    @Autowired
    private JobAgeRelaxationService jobAgeRelaxationService;

    @Autowired
    private JobApplicationFeeService jobApplicationFeeService;

    @Autowired
    private JobPostingLocationService jobPostingLocationService;

    @Autowired
    private JobSelectionProcessService jobSelectionProcessService;

    @Autowired
    private JobVacanciesService jobVacanciesService;
    @Autowired
    private JobPositionsRepository jobPositionsRepository;

    //microservices
    private final JobRequisitionsService jobRequisitionsService;

    public JobPositionService(@Lazy JobRequisitionsService jobRequisitionsService) {
        this.jobRequisitionsService = jobRequisitionsService;
    }


    private JobPositionsDTO setValuesDTO(PositionsEntity positions,
                                         JobPostingLocationEntity jobPostingLocation,
                                         JobVacanciesEntity jobVacancies,
                                         JobSelectionProcessEntity jobSelectionProcess){
        JobPositionsDTO positionsDTO = new JobPositionsDTO();
        //postion data
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

        // position code
        positionsDTO.setPosition_code(positions.getPositionCode());
        // status
        positionsDTO.setPosition_status(positions.getPositionStatus());

        if ((jobPostingLocation ==null) ) {
            positionsDTO.setLocation_id(null);
            positionsDTO.setDept_id(null);
        } else {
            positionsDTO.setLocation_id(jobPostingLocation.getLocationId());
            positionsDTO.setDept_id(jobPostingLocation.getDeptId());
        }

        //job selection process
        if ((jobSelectionProcess == null)) {
            positionsDTO.setSelection_procedure(null);
        } else{
            positionsDTO.setSelection_procedure(jobSelectionProcess.getSelectionProcedure());
        }


        //job vacancies
        if((jobVacancies == null)) {
            positionsDTO.setNo_of_vacancies(null);
            positionsDTO.setSpecial_cat_id(null);
            positionsDTO.setReservation_cat_id(null);
        } else {
            positionsDTO.setNo_of_vacancies(jobVacancies.getNoOfVacancies());
            positionsDTO.setSpecial_cat_id(jobVacancies.getSpecialCatId());
            positionsDTO.setReservation_cat_id(jobVacancies.getReservationCatId());
        }

        return positionsDTO;
    }

    private ResponseDTO setResponseDTO(PositionsEntity positions,
                                       JobPostingLocationEntity jobPostingLocation,
                                       JobVacanciesEntity jobVacancies,
                                       JobSelectionProcessEntity jobSelectionProcess){
        ResponseDTO positionsDTO = new ResponseDTO();
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

        // position code
        positionsDTO.setPosition_code(positions.getPositionCode());
        // status
        positionsDTO.setPosition_status(positions.getPositionStatus());

        if (jobPostingLocation ==null ) {
            positionsDTO.setLocation_id(null);
            positionsDTO.setDept_id(null);
            positionsDTO.setCity_id(null);
            positionsDTO.setState_id(null);
            positionsDTO.setCountry_id(null);
        } else {
            positionsDTO.setLocation_id(jobPostingLocation.getLocationId());
            positionsDTO.setDept_id(jobPostingLocation.getDeptId());
            Long cityId = jobPostingLocationService.findCityBylocationId(jobPostingLocation.getLocationId());
            long stateId = jobPostingLocationService.findStateByCityId(cityId);
            long countryId = jobPostingLocationService.findCountryByStateId(stateId);
            positionsDTO.setCity_id(cityId);
            positionsDTO.setState_id(stateId);
            positionsDTO.setCountry_id(countryId);
        }

        //job selection process
        if ((jobSelectionProcess == null)) {
            positionsDTO.setSelection_procedure(null);
        } else{
            positionsDTO.setSelection_procedure(jobSelectionProcess.getSelectionProcedure());
        }


        //job vacancies
        if((jobVacancies == null)) {
            positionsDTO.setNo_of_vacancies(null);
            positionsDTO.setSpecial_cat_id(null);
            positionsDTO.setReservation_cat_id(null);
        } else {
            positionsDTO.setNo_of_vacancies(jobVacancies.getNoOfVacancies());
            positionsDTO.setSpecial_cat_id(jobVacancies.getSpecialCatId());
            positionsDTO.setReservation_cat_id(jobVacancies.getReservationCatId());
        }
        return  positionsDTO;
    }

    private PositionsEntity setValues(JobPositionsDTO positionsDTO){
        PositionsEntity positions = new PositionsEntity();
        positions.setPositionId(positionsDTO.getPosition_id());
        positions.setPositionCode("JPOS"+(5000+jobPositionsRepository.count()));
        positions.setRequisitionId(positionsDTO.getRequisition_id());
        positions.setPositionTitle(positionsDTO.getPosition_title());
        positions.setDescription(positionsDTO.getDescription());
        positions.setRolesResponsibilities(positionsDTO.getRoles_responsibilities());
        positions.setGradeId(positionsDTO.getGrade_id());
        positions.setEmploymentType(positionsDTO.getEmployment_type());
        positions.setEligibilityAgeMin(positionsDTO.getEligibility_age_min());
        positions.setEligibilityAgeMax(positionsDTO.getEligibility_age_max());
        positions.setMandatoryQualification(positionsDTO.getMandatory_qualification());
        positions.setPreferredQualification(positionsDTO.getPreferred_qualification());
        positions.setMandatoryExperience(positionsDTO.getMandatory_experience());
        positions.setPreferredExperience(positionsDTO.getPreferred_experience());
        positions.setProbationPeriod(positionsDTO.getProbation_period());
        positions.setDocumentsRequired(positionsDTO.getDocuments_required());
        positions.setMinCreditScore(positionsDTO.getMin_credit_score());
        positions.setPositionStatus("Active");
        LocalDateTime time = LocalDateTime.now();
        positions.setCreatedBy("");
        positions.setCreatedDate(time);
        positions.setUpdatedBy("");
        positions.setUpdatedDate(time);
        return positions;

    }
    // save to repo
    @Transactional
    public JobPositionsDTO createPosition(JobPositionsDTO jobPositionsDTO){
        UUID position_id = UUID.randomUUID();
        jobPositionsDTO.setPosition_id(position_id);

        PositionsEntity positions = setValues(jobPositionsDTO);
        PositionsEntity positions1 =jobPositionsRepository.save(positions);


        //posting location - service
        JobPostingLocationEntity jobPostingLocation = jobPostingLocationService.createPostingLocation(jobPositionsDTO,position_id);

        //selection process - service

        JobSelectionProcessEntity jobSelectionProcess =jobSelectionProcessService.createSelectionProcess(jobPositionsDTO,position_id);

        //vacancies - services
        JobVacanciesEntity jobVacancies = jobVacanciesService.createJobVacancies(jobPositionsDTO,position_id);

        return setValuesDTO(positions1,jobPostingLocation,jobVacancies,jobSelectionProcess);
    }
    // Bulk creation
    @Transactional
    public List<JobPositionsDTO> createBulkPostions(List<JobPositionsDTO> jobPositionsDTOS){
        List<JobPositionsDTO> jobPositionsDTOList = new ArrayList<>();
        for(JobPositionsDTO jobs: jobPositionsDTOS){
            jobPositionsDTOList.add(createPosition(jobs));
        }
        return jobPositionsDTOList;
    }

    // get by recId
    @Transactional
    public List<JobPositionsDTO> findByReqId(UUID requisition_id){
        List<PositionsEntity> jobposition = jobPositionsRepository.findAllByRequisitionId(requisition_id);
        List<JobPositionsDTO> result = new ArrayList<>();
        for(PositionsEntity position :jobposition ){
            if (position.getPositionStatus().equals("Active") ){
                UUID position_id = position.getPositionId();
                JobPostingLocationEntity jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(position_id);
                JobSelectionProcessEntity jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(position_id);
                JobVacanciesEntity jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(position_id);
                JobPositionsDTO jobPositionsDTO = setValuesDTO(position,jobPostingLocation,jobVacancies,jobSelectionProcess);
                result.add(jobPositionsDTO);
            }
        }
        return result;
    }
    //get by posId
    @Transactional
    public ResponseDTO findByPositionId(UUID position_id){
        PositionsEntity position = jobPositionsRepository.findById(position_id).orElse(null);
        if (position == null) return null;
        if (position.getPositionStatus().equals("Active") ) {
            JobPostingLocationEntity jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(position_id);
            JobSelectionProcessEntity jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(position_id);
            JobVacanciesEntity jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(position_id);
            ResponseDTO jobPositionsDTO = setResponseDTO(position, jobPostingLocation,  jobVacancies, jobSelectionProcess);
            return jobPositionsDTO;
        }
        return null;
    }
    //get all
    public List<JobPositionsDTO> findAllPositions(){
        List<PositionsEntity> positionsList = jobPositionsRepository.findAll();
        List<JobPositionsDTO> jobPositionsDTOList = new ArrayList<>();
        for(PositionsEntity positions:positionsList) {
            if (positions.getPositionStatus().equals("Active")) {
                UUID position_id = positions.getPositionId();
                JobPostingLocationEntity jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(position_id);
                JobSelectionProcessEntity jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(position_id);
                JobVacanciesEntity jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(position_id);
                JobPositionsDTO jobPositionsDTO = setValuesDTO(positions, jobPostingLocation, jobVacancies,  jobSelectionProcess);
                jobPositionsDTOList.add(jobPositionsDTO);
            }
        }
        return jobPositionsDTOList;
    }


    // update by posId
    public JobPositionsDTO updateJobposition(JobPositionsDTO jobPositionsDTO){
        UUID positionId = jobPositionsDTO.getPosition_id();
        PositionsEntity existingPosition = jobPositionsRepository.findById(positionId).orElse(null);
        if (existingPosition == null) {
            return null; // or throw an exception
        }
        // Update the existing position with new values
        existingPosition.setRequisitionId(jobPositionsDTO.getRequisition_id());

        existingPosition.setPositionTitle(jobPositionsDTO.getPosition_title());
        existingPosition.setDescription(jobPositionsDTO.getDescription());
        existingPosition.setRolesResponsibilities(jobPositionsDTO.getRoles_responsibilities());
        existingPosition.setGradeId(jobPositionsDTO.getGrade_id());
        existingPosition.setEmploymentType(jobPositionsDTO.getEmployment_type());
        existingPosition.setEligibilityAgeMin(jobPositionsDTO.getEligibility_age_min());
        existingPosition.setEligibilityAgeMax(jobPositionsDTO.getEligibility_age_max());
        existingPosition.setMandatoryQualification(jobPositionsDTO.getMandatory_qualification());
        existingPosition.setPreferredQualification(jobPositionsDTO.getPreferred_qualification());
        existingPosition.setMandatoryExperience(jobPositionsDTO.getMandatory_experience());
        existingPosition.setPreferredExperience(jobPositionsDTO.getPreferred_experience());
        existingPosition.setProbationPeriod(jobPositionsDTO.getProbation_period());
        existingPosition.setDocumentsRequired(jobPositionsDTO.getDocuments_required());
        existingPosition.setMinCreditScore(jobPositionsDTO.getMin_credit_score());
        existingPosition.setUpdatedBy(""); // Set the updated_by field as needed
        existingPosition.setUpdatedDate(LocalDateTime.now()); // Update the timestamp

        // Save the updated position
        jobPositionsRepository.save(existingPosition);

        // posting location
        JobPostingLocationEntity jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(positionId);
        if (jobPostingLocation == null) {
            jobPostingLocation = jobPostingLocationService.createPostingLocation(jobPositionsDTO, positionId);
        } else {
            jobPostingLocationService.updatePostingLocation(jobPositionsDTO, positionId);
        }
        //selection process
        JobSelectionProcessEntity jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(positionId);
        if (jobSelectionProcess == null) {
            jobSelectionProcess = jobSelectionProcessService.createSelectionProcess(jobPositionsDTO, positionId);
        } else {
            jobSelectionProcessService.updateSelectionProcess(jobPositionsDTO, positionId);
        }
        //job vacancies
        JobVacanciesEntity jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(positionId);
        if (jobVacancies == null) {
            jobVacancies = jobVacanciesService.createJobVacancies(jobPositionsDTO, positionId);
        } else {
            jobVacanciesService.updateJobVacancies(jobPositionsDTO, positionId);
        }
        // Create a new JobPositionsDTO with the updated values
        jobPositionsDTO = setValuesDTO(existingPosition, jobPostingLocation, jobVacancies, jobSelectionProcess);
        // Return the updated JobPositionsDTO


        return jobPositionsDTO;
    }


    //delete softly
    public String delectByPositionId(UUID positionId){
        ResponseDTO positions = findByPositionId(positionId);
        if (positions == null) {
            return "Position not found";
        }
        PositionsEntity position = jobPositionsRepository.findById(positionId).orElse(null);
        if (position != null) {
            position.setPositionStatus("Inactive");
            jobPositionsRepository.save(position);
            return "Position deleted successfully";
        } else {
            return "Position not found";
        }
    }

    @Transactional
    public List<JobPositionsDTO> getActiveJobs() {
        List<JobRequisitionsEntity> activeRequisitions = jobRequisitionsService.getActiveRequisitions();
        List<JobPositionsDTO> activePositions = new ArrayList<>();
        for( JobRequisitionsEntity requisition : activeRequisitions) {
            List<PositionsEntity> positions = jobPositionsRepository.findAllByRequisitionId(requisition.getRequisitionId());
            for (PositionsEntity position : positions) {
                if (position.getPositionStatus().equals("Active")) {
                    JobPostingLocationEntity jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(position.getPositionId());
                    JobSelectionProcessEntity jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(position.getPositionId());
                    JobVacanciesEntity jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(position.getPositionId());
                    JobPositionsDTO jobPositionsDTO = setValuesDTO(position, jobPostingLocation, jobVacancies, jobSelectionProcess);
                    jobPositionsDTO.setRequisition_code(requisition.getRequisitionCode());
                    jobPositionsDTO.setRequisition_title(requisition.getRequisitionTitle());
                    activePositions.add(jobPositionsDTO);
                }
            }
        }
        return activePositions;
    }

    @Transactional
    public List<JobPositionsDTO> findByJobTitleAndLocation(String jobTitle, Long location) {
        List<JobPositionsDTO> positionsList = getActiveJobs();
        List<JobPositionsDTO> filteredPositions = new ArrayList<>();
        for (JobPositionsDTO position : positionsList) {
            if (position.getPosition_title().equalsIgnoreCase(jobTitle) && position.getLocation_id() != null && position.getLocation_id().equals(location)) {
                filteredPositions.add(position);
            }
        }
        return filteredPositions;
    }
}