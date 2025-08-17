package com.example.JobCreation.service;

import com.example.JobCreation.dto.JobPositionsDTO;
import com.example.JobCreation.dto.ResponseDTO;
import com.example.JobCreation.model.*;
import com.example.JobCreation.repository.JobPositionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private JobRequisitionsService jobRequisitionsService;



    private JobPositionsDTO setValuesDTO(Positions positions,
                                         JobPostingLocation jobPostingLocation,
                                         JobVacancies jobVacancies,
                                         JobSelectionProcess jobSelectionProcess){
        JobPositionsDTO positionsDTO = new JobPositionsDTO();
        //postion data
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

        // position code
        positionsDTO.setPosition_code(positions.getPosition_code());
        // status
        positionsDTO.setPosition_status(positions.getPosition_status());

        if ((jobPostingLocation ==null) ) {
            positionsDTO.setLocation_id(null);
            positionsDTO.setDept_id(null);
        } else {
            positionsDTO.setLocation_id(jobPostingLocation.getLocation_id());
            positionsDTO.setDept_id(jobPostingLocation.getDept_id());
        }

        //job selection process
        if ((jobSelectionProcess == null)) {
            positionsDTO.setSelection_procedure(null);
        } else{
            positionsDTO.setSelection_procedure(jobSelectionProcess.getSelection_procedure());
        }


        //job vacancies
        if((jobVacancies == null)) {
            positionsDTO.setNo_of_vacancies(null);
            positionsDTO.setSpecial_cat_id(null);
            positionsDTO.setReservation_cat_id(null);
        } else {
            positionsDTO.setNo_of_vacancies(jobVacancies.getNo_of_vacancies());
            positionsDTO.setSpecial_cat_id(jobVacancies.getSpecial_cat_id());
            positionsDTO.setReservation_cat_id(jobVacancies.getReservation_cat_id());
        }




        return positionsDTO;
    }

    private ResponseDTO setResponseDTO(Positions positions,
                                       JobPostingLocation jobPostingLocation,
                                       JobVacancies jobVacancies,
                                       JobSelectionProcess jobSelectionProcess){
        ResponseDTO positionsDTO = new ResponseDTO();
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

        // position code
        positionsDTO.setPosition_code(positions.getPosition_code());
        // status
        positionsDTO.setPosition_status(positions.getPosition_status());

        if (jobPostingLocation ==null ) {
            positionsDTO.setLocation_id(null);
            positionsDTO.setDept_id(null);
            positionsDTO.setCity_id(null);
            positionsDTO.setState_id(null);
            positionsDTO.setCountry_id(null);
        } else {
            positionsDTO.setLocation_id(jobPostingLocation.getLocation_id());
            positionsDTO.setDept_id(jobPostingLocation.getDept_id());
            Long cityId = jobPostingLocationService.findCityBylocationId(jobPostingLocation.getLocation_id());
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
            positionsDTO.setSelection_procedure(jobSelectionProcess.getSelection_procedure());
        }


        //job vacancies
        if((jobVacancies == null)) {
            positionsDTO.setNo_of_vacancies(null);
            positionsDTO.setSpecial_cat_id(null);
            positionsDTO.setReservation_cat_id(null);
        } else {
            positionsDTO.setNo_of_vacancies(jobVacancies.getNo_of_vacancies());
            positionsDTO.setSpecial_cat_id(jobVacancies.getSpecial_cat_id());
            positionsDTO.setReservation_cat_id(jobVacancies.getReservation_cat_id());
        }
        return  positionsDTO;
    }

    private Positions setValues(JobPositionsDTO positionsDTO){
        Positions positions = new Positions();
        positions.setPosition_id(positionsDTO.getPosition_id());
        positions.setPosition_code("JPOS"+(5000+jobPositionsRepository.count()));
        positions.setRequisition_id(positionsDTO.getRequisition_id());
        positions.setPosition_title(positionsDTO.getPosition_title());
        positions.setDescription(positionsDTO.getDescription());
        positions.setRoles_responsibilities(positionsDTO.getRoles_responsibilities());
        positions.setGrade_id(positionsDTO.getGrade_id());
        positions.setEmployment_type(positionsDTO.getEmployment_type());
        positions.setEligibility_age_min(positionsDTO.getEligibility_age_min());
        positions.setEligibility_age_max(positionsDTO.getEligibility_age_max());
        positions.setMandatory_qualification(positionsDTO.getMandatory_qualification());
        positions.setPreferred_qualification(positionsDTO.getPreferred_qualification());
        positions.setMandatory_experience(positionsDTO.getMandatory_experience());
        positions.setPreferred_experience(positionsDTO.getPreferred_experience());
        positions.setProbation_period(positionsDTO.getProbation_period());
        positions.setDocument_required(positionsDTO.getDocuments_required());
        positions.setMin_credit_score(positionsDTO.getMin_credit_score());
        positions.setPosition_status("Active");
        LocalDateTime time = LocalDateTime.now();
        positions.setCreated_by("");
        positions.setCreated_date(time);
        positions.setUpdated_by("");
        positions.setUpdated_date(time);
        return positions;

    }
    // save to repo
    @Transactional
    public JobPositionsDTO createPosition(JobPositionsDTO jobPositionsDTO){
        UUID position_id = UUID.randomUUID();
        jobPositionsDTO.setPosition_id(position_id);

        Positions positions = setValues(jobPositionsDTO);
        Positions positions1 =jobPositionsRepository.save(positions);


        //posting location - service
        JobPostingLocation jobPostingLocation = jobPostingLocationService.createPostingLocation(jobPositionsDTO,position_id);

        //selection process - service

        JobSelectionProcess jobSelectionProcess =jobSelectionProcessService.createSelectionProcess(jobPositionsDTO,position_id);

        //vacancies - services
        JobVacancies jobVacancies = jobVacanciesService.createJobVacancies(jobPositionsDTO,position_id);

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
        List<Positions> jobposition = jobPositionsRepository.findAllByRequisitionId(requisition_id);
        List<JobPositionsDTO> result = new ArrayList<>();
        for(Positions position :jobposition ){
            if (position.getPosition_status().equals("Active") ){
                UUID position_id = position.getPosition_id();
                JobPostingLocation jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(position_id);
                JobSelectionProcess jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(position_id);
                JobVacancies jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(position_id);
                JobPositionsDTO jobPositionsDTO = setValuesDTO(position,jobPostingLocation,jobVacancies,jobSelectionProcess);
                result.add(jobPositionsDTO);
            }
        }
        return result;
    }
    //get by posId
    @Transactional
    public ResponseDTO findByPositionId(UUID position_id){
        Positions position = jobPositionsRepository.findById(position_id).orElse(null);
        if (position == null) return null;
        if (position.getPosition_status().equals("Active") ) {
            JobPostingLocation jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(position_id);
            JobSelectionProcess jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(position_id);
            JobVacancies jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(position_id);
            ResponseDTO jobPositionsDTO = setResponseDTO(position, jobPostingLocation,  jobVacancies, jobSelectionProcess);
            return jobPositionsDTO;
        }
        return null;
    }
    //get all
    public List<JobPositionsDTO> findAllPositions(){
        List<Positions> positionsList = jobPositionsRepository.findAll();
        List<JobPositionsDTO> jobPositionsDTOList = new ArrayList<>();
        for(Positions positions:positionsList) {
            if (positions.getPosition_status().equals("Active")) {
                UUID position_id = positions.getPosition_id();
                JobPostingLocation jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(position_id);
                JobSelectionProcess jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(position_id);
                JobVacancies jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(position_id);
                JobPositionsDTO jobPositionsDTO = setValuesDTO(positions, jobPostingLocation, jobVacancies,  jobSelectionProcess);
                jobPositionsDTOList.add(jobPositionsDTO);
            }
        }
        return jobPositionsDTOList;
    }


    // update by posId
    public JobPositionsDTO updateJobposition(JobPositionsDTO jobPositionsDTO){
        UUID positionId = jobPositionsDTO.getPosition_id();
        Positions existingPosition = jobPositionsRepository.findById(positionId).orElse(null);
        if (existingPosition == null) {
            return null; // or throw an exception
        }
        // Update the existing position with new values
        existingPosition.setRequisition_id(jobPositionsDTO.getRequisition_id());

        existingPosition.setPosition_title(jobPositionsDTO.getPosition_title());
        existingPosition.setDescription(jobPositionsDTO.getDescription());
        existingPosition.setRoles_responsibilities(jobPositionsDTO.getRoles_responsibilities());
        existingPosition.setGrade_id(jobPositionsDTO.getGrade_id());
        existingPosition.setEmployment_type(jobPositionsDTO.getEmployment_type());
        existingPosition.setEligibility_age_min(jobPositionsDTO.getEligibility_age_min());
        existingPosition.setEligibility_age_max(jobPositionsDTO.getEligibility_age_max());
        existingPosition.setMandatory_qualification(jobPositionsDTO.getMandatory_qualification());
        existingPosition.setPreferred_qualification(jobPositionsDTO.getPreferred_qualification());
        existingPosition.setMandatory_experience(jobPositionsDTO.getMandatory_experience());
        existingPosition.setPreferred_experience(jobPositionsDTO.getPreferred_experience());
        existingPosition.setProbation_period(jobPositionsDTO.getProbation_period());
        existingPosition.setDocument_required(jobPositionsDTO.getDocuments_required());
        existingPosition.setMin_credit_score(jobPositionsDTO.getMin_credit_score());
        existingPosition.setUpdated_by(""); // Set the updated_by field as needed
        existingPosition.setUpdated_date(LocalDateTime.now()); // Update the timestamp

        // Save the updated position
        jobPositionsRepository.save(existingPosition);

        // posting location
        JobPostingLocation jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(positionId);
        if (jobPostingLocation == null) {
            jobPostingLocation = jobPostingLocationService.createPostingLocation(jobPositionsDTO, positionId);
        } else {
            jobPostingLocationService.updatePostingLocation(jobPositionsDTO, positionId);
        }
        //selection process
        JobSelectionProcess jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(positionId);
        if (jobSelectionProcess == null) {
            jobSelectionProcess = jobSelectionProcessService.createSelectionProcess(jobPositionsDTO, positionId);
        } else {
            jobSelectionProcessService.updateSelectionProcess(jobPositionsDTO, positionId);
        }
        //job vacancies
        JobVacancies jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(positionId);
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
        Positions position = jobPositionsRepository.findById(positionId).orElse(null);
        if (position != null) {
            position.setPosition_status("Inactive");
            jobPositionsRepository.save(position);
            return "Position deleted successfully";
        } else {
            return "Position not found";
        }
    }

    @Transactional
    public List<JobPositionsDTO> getActiveJobs() {
        List<JobRequisitions> activeRequisitions = jobRequisitionsService.getActiveRequisitions();
        List<JobPositionsDTO> activePositions = new ArrayList<>();
        for( JobRequisitions requisition : activeRequisitions) {
            List<Positions> positions = jobPositionsRepository.findAllByRequisitionId(requisition.getRequisition_id());
            for (Positions position : positions) {
                if (position.getPosition_status().equals("Active")) {
                    JobPostingLocation jobPostingLocation = jobPostingLocationService.getByPositionIdPostingLocation(position.getPosition_id());
                    JobSelectionProcess jobSelectionProcess = jobSelectionProcessService.getByPositionIdSelectionProcess(position.getPosition_id());
                    JobVacancies jobVacancies = jobVacanciesService.getByPositionIdJobVacancies(position.getPosition_id());
                    JobPositionsDTO jobPositionsDTO = setValuesDTO(position, jobPostingLocation, jobVacancies, jobSelectionProcess);
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