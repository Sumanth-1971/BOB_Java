//package com.example.JobCreation.service;
//
//import com.example.JobCreation.dto.JobPositionsDTO;
//import com.example.JobCreation.model.*;
//import com.example.JobCreation.repository.*;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class JobPositionsService {
//
//    @Autowired
//    private JobPositionsRepository jobPositionsRepository;
//
//    @Autowired
//    private JobAgeRelaxationsRepository jobAgeRelaxationsRepository;
//
//    @Autowired
//    private JobApplicationFeeRepository jobApplicationFeeRepository;
//
//    @Autowired
//    private JobPostingLocationRepository jobPostingLocationRepository;
//
//    @Autowired
//    private JobSelectionProcessRepository jobSelectionProcessRepository;
//
//    @Autowired
//    private JobVacanciesRepository jobVacanciesRepository;
//    private Positions setJobpostioning(JobPositionsDTO positionsDTO){
//        Positions positions =new Positions();
//        positions.setPosition_id(positionsDTO.getPosition_id());
//        positions.setPosition_code("JPOS"+(5000+jobPositionsRepository.count()));
//        positions.setRequisition_id(positionsDTO.getRequisition_id());
//        positions.setPosition_title(positionsDTO.getPosition_title());
//        positions.setDescription(positionsDTO.getDescription());
//        positions.setRoles_responsibilities(positionsDTO.getRoles_responsibilities());
//        positions.setGrade_id(positionsDTO.getGrade_id());
//        positions.setEmployment_type(positionsDTO.getEmployment_type());
//        positions.setEligibility_age_min(positionsDTO.getEligibility_age_min());
//        positions.setEligibility_age_max(positionsDTO.getEligibility_age_max());
//        positions.setMandatory_qualification(positionsDTO.getMandatory_qualification());
//        positions.setPreferred_qualification(positionsDTO.getPreferred_qualification());
//        positions.setMandatory_experience(positionsDTO.getMandatory_experience());
//        positions.setPreferred_experience(positionsDTO.getPreferred_experience());
//        positions.setProbation_period(positionsDTO.getProbation_period());
//        positions.setDocuments_required(positionsDTO.getDocuments_required());
//        positions.setMin_credit_score(positionsDTO.getMin_credit_score());
//        positions.setPosition_status("Submitted");
//        LocalDateTime time = LocalDateTime.now();
//        positions.setCreated_by("");
//        positions.setCreated_date(time);
//        positions.setUpdated_by("");
//        positions.setUpdated_date(time);
//        return positions;
//    }
//    private JobPostingLocation setJobPostingLocation(JobPositionsDTO positionsDTO){
//        JobPostingLocation jobPostingLocation = new JobPostingLocation();
//        jobPostingLocation.setDept_id(positionsDTO.getDept_id());
//        jobPostingLocation.setLocation_id(positionsDTO.getLocation_id());
//        jobPostingLocation.setPosition_id(positionsDTO.getPosition_id());
//        return jobPostingLocation;
//    }
//    private JobApplicationFee setJobApplicationFee(JobPositionsDTO positionsDTO){
//        JobApplicationFee jobApplicationFee =new JobApplicationFee();
//        jobApplicationFee.setPosition_id(positionsDTO.getPosition_id());
//        //Default 1
//        jobApplicationFee.setApplication_fee_id(1);
//        return jobApplicationFee;
//    }
//    private JobVacancies setJobVacancies(JobPositionsDTO positionsDTO){
//        JobVacancies jobVacancies = new JobVacancies();
//        jobVacancies.setPosition_id(positionsDTO.getPosition_id());
//        jobVacancies.setLocation_id(positionsDTO.getLocation_id());
//        jobVacancies.setNo_of_vacancies(positionsDTO.getNo_of_vacancies());
//
//        //default 1
//        jobVacancies.setSpecial_cat_id(1);
//        jobVacancies.setReservation_cat_id(1);
//        return jobVacancies;
//    }
//    private JobAgeRelaxations setJobAgeRelaxations(JobPositionsDTO positionsDTO){
//        JobAgeRelaxations jobAgeRelaxations = new JobAgeRelaxations();
//        jobAgeRelaxations.setPosition_id(positionsDTO.getPosition_id());
//
//        //default 1
//        jobAgeRelaxations.setAge_relaxation_id(1);
//
//        return jobAgeRelaxations;
//    }
//    private JobSelectionProcess setJobSelectionProcess(JobPositionsDTO positionsDTO){
//        JobSelectionProcess jobSelectionProcess = new JobSelectionProcess();
//        jobSelectionProcess.setPosition_id(positionsDTO.getPosition_id());
//        jobSelectionProcess.setSelection_procedure(positionsDTO.getSelection_procedure());
//        return jobSelectionProcess;
//    }
//
//    private Positions setJobpostioningUpdate(JobPositionsDTO positionsDTO){
//        Positions positions =new Positions();
//        positions.setPosition_id(positionsDTO.getPosition_id());
//        positions.setRequisition_id(positionsDTO.getRequisition_id());
//        positions.setPosition_title(positionsDTO.getPosition_title());
//        positions.setPosition_code(positionsDTO.getPosition_code());
//        positions.setPosition_status(positionsDTO.getPosition_status());
//        positions.setDescription(positionsDTO.getDescription());
//        positions.setRoles_responsibilities(positionsDTO.getRoles_responsibilities());
//        positions.setGrade_id(positionsDTO.getGrade_id());
//        positions.setEmployment_type(positionsDTO.getEmployment_type());
//        positions.setEligibility_age_min(positionsDTO.getEligibility_age_min());
//        positions.setEligibility_age_max(positionsDTO.getEligibility_age_max());
//        positions.setMandatory_qualification(positionsDTO.getMandatory_qualification());
//        positions.setPreferred_qualification(positionsDTO.getPreferred_qualification());
//        positions.setMandatory_experience(positionsDTO.getMandatory_experience());
//        positions.setPreferred_experience(positionsDTO.getPreferred_experience());
//        positions.setProbation_period(positionsDTO.getProbation_period());
//        positions.setDocuments_required(positionsDTO.getDocuments_required());
//        positions.setMin_credit_score(positionsDTO.getMin_credit_score());
//        LocalDateTime time = LocalDateTime.now();
//        positions.setUpdated_by("");
//        positions.setUpdated_date(time);
//        return positions;
//    }
//
//
//
//    @Transactional
//    public JobPositionsDTO createPositions(JobPositionsDTO positionsDTO){
//
//        positionsDTO.setPosition_id(UUID.randomUUID());
//        Positions positions = setJobpostioning(positionsDTO);
//
//
//        // Save main table first
//        jobPositionsRepository.save(positions);
//
//        // Now create & save supporting tables
//        JobPostingLocation jobPostingLocation = setJobPostingLocation(positionsDTO);
//        //JobApplicationFee jobApplicationFee = setJobApplicationFee(positionsDTO);
//        JobVacancies jobVacancies = setJobVacancies(positionsDTO);
//        //JobAgeRelaxations jobAgeRelaxations = setJobAgeRelaxations(positionsDTO);
//        JobSelectionProcess jobSelectionProcess = setJobSelectionProcess(positionsDTO);
//
//        //jobAgeRelaxationsRepository.save(jobAgeRelaxations);
//        //jobApplicationFeeRepository.save(jobApplicationFee);
//        jobPostingLocationRepository.save(jobPostingLocation);
//        jobVacanciesRepository.save(jobVacancies);
//        jobSelectionProcessRepository.save(jobSelectionProcess);
//
//        return positionsDTO;
//    }
//
//    @Transactional
//    public JobPositionsDTO updatePositions(JobPositionsDTO positionsDTO){
//        Positions positions =  setJobpostioningUpdate(positionsDTO);
//        JobPostingLocation jobPostingLocation = setJobPostingLocation(positionsDTO);
//        //JobApplicationFee jobApplicationFee = setJobApplicationFee(positionsDTO);
//        JobVacancies jobVacancies = setJobVacancies(positionsDTO);
//        //JobAgeRelaxations jobAgeRelaxations = setJobAgeRelaxations(positionsDTO);
//        //JobSelectionProcess jobSelectionProcess = setJobSelectionProcess(positionsDTO);
//
//        //position update
//        jobPositionsRepository.save(positions);
//
//
////        jobAgeRelaxationsRepository.updateByPositionId(
////                jobAgeRelaxations.getPosition_id(),
////                jobAgeRelaxations.getAge_relaxation_id()
////        );
////
////
////        jobApplicationFeeRepository.updateByPositionId(
////                jobApplicationFee.getPosition_id(),
////                jobApplicationFee.getJob_application_fee_id()
////        );
//        jobPostingLocationRepository.updateByPositionId(
//                jobPostingLocation.getPosition_id(),
//                jobPostingLocation.getLocation_id(),
//                jobPostingLocation.getDept_id()
//        );
//        jobVacanciesRepository.updateByPositionId(
//                jobVacancies.getPosition_id(),
//                jobVacancies.getSpecial_cat_id(),
//                jobVacancies.getReservation_cat_id(),
//                jobVacancies.getLocation_id(),
//                jobVacancies.getNo_of_vacancies()
//        );
////        jobSelectionProcessRepository.updateByPositionId(
////                jobSelectionProcess.getPosition_id(),
////                jobSelectionProcess.getSelection_procedure()
////        );
//
//        return positionsDTO;
//    }
//
//    public JobPositionsDTO getByPositionId(UUID position_id){
//        Positions positions = jobPositionsRepository.findById(position_id).orElse(null);
//        JobPostingLocation jobPostingLocation = jobPostingLocationRepository.findByPositionId(position_id);
//        JobApplicationFee jobApplicationFee = jobApplicationFeeRepository.findByPositionId(position_id);
//        JobVacancies jobVacancies = jobVacanciesRepository.findByPositionId(position_id);
//        JobAgeRelaxations jobAgeRelaxations = jobAgeRelaxationsRepository.findByPositionId(position_id);
//        JobSelectionProcess jobSelectionProcess = jobSelectionProcessRepository.findByPositionId(position_id);
//        if (positions == null) {
//            return null; // or throw an exception
//        }
//        JobPositionsDTO positionsDTO = setJobPositionsDTO(positions ,jobPostingLocation, jobApplicationFee, jobVacancies, jobAgeRelaxations, jobSelectionProcess);
//        return  positionsDTO;
//    }
//
//    private JobPositionsDTO setJobPositionsDTO( Positions positions,
//                                                JobPostingLocation jobPostingLocation,
//                                                JobApplicationFee jobApplicationFee,
//                                                JobVacancies jobVacancies,
//                                                JobAgeRelaxations jobAgeRelaxations,
//                                                JobSelectionProcess jobSelectionProcess) {
//        JobPositionsDTO positionsDTO = new JobPositionsDTO();
//        //postion data
//        positionsDTO.setPosition_id(positions.getPosition_id());
//        positionsDTO.setRequisition_id(positions.getRequisition_id());
//        positionsDTO.setPosition_title(positions.getPosition_title());
//        positionsDTO.setDescription(positions.getDescription());
//        positionsDTO.setRoles_responsibilities(positions.getRoles_responsibilities());
//        positionsDTO.setGrade_id(positions.getGrade_id());
//        positionsDTO.setEmployment_type(positions.getEmployment_type());
//        positionsDTO.setEligibility_age_min(positions.getEligibility_age_min());
//        positionsDTO.setEligibility_age_max(positions.getEligibility_age_max());
//        positionsDTO.setMandatory_qualification(positions.getMandatory_qualification());
//        positionsDTO.setPreferred_qualification(positions.getPreferred_qualification());
//        positionsDTO.setMandatory_experience(positions.getMandatory_experience());
//        positionsDTO.setPreferred_experience(positions.getPreferred_experience());
//        positionsDTO.setProbation_period(positions.getProbation_period());
//        positionsDTO.setDocuments_required(positions.getDocument_required());
//        positionsDTO.setMin_credit_score(positions.getMin_credit_score());
//
//        //job posting location
//
//        if( jobPostingLocation == null) {
//            jobPostingLocation = new JobPostingLocation();
//            jobPostingLocation.setPosition_id(positions.getPosition_id());
//            jobPostingLocation.setDept_id(0); // Default value if not set
//            jobPostingLocation.setLocation_id(0); // Default value if not set
//        }else {
//            positionsDTO.setLocation_id(jobPostingLocation.getLocation_id());
//            positionsDTO.setDept_id(jobPostingLocation.getDept_id());
//        }
//
//        if( jobApplicationFee == null) {
//            jobApplicationFee = new JobApplicationFee();
//            jobApplicationFee.setPosition_id(positions.getPosition_id());
//            jobApplicationFee.setJob_application_fee_id(0); // Default value if not set
//            jobApplicationFee.setApplication_fee_id(1); // Default value if not set
//        }
////        else {
////            positionsDTO.setApplication_fee_id(jobApplicationFee.getApplication_fee_id());
////        }
//        //job application fee
//
//
//        if (jobVacancies == null) {
//            jobVacancies = new JobVacancies();
//            jobVacancies.setPosition_id(positions.getPosition_id());
//            jobVacancies.setNo_of_vacancies(0); // Default value if not set
//            jobVacancies.setSpecial_cat_id(1); // Default value if not set
//            jobVacancies.setReservation_cat_id(1); // Default value if not set
//        } else {
//            positionsDTO.setNo_of_vacancies(jobVacancies.getNo_of_vacancies());
//            positionsDTO.setSpecial_cat_id(jobVacancies.getSpecial_cat_id());
//            positionsDTO.setReservation_cat_id(jobVacancies.getReservation_cat_id());
//        }
//
////        if (jobAgeRelaxations == null) {
////            jobAgeRelaxations = new JobAgeRelaxations();
////            jobAgeRelaxations.setPosition_id(positions.getPosition_id());
////            jobAgeRelaxations.setAge_relaxation_id(1); // Default value if not set
////        } else {
////            positionsDTO.setAge_relaxation_id(jobAgeRelaxations.getAge_relaxation_id());
////        }
//
//        if (jobSelectionProcess == null) {
//            jobSelectionProcess = new JobSelectionProcess();
//            jobSelectionProcess.setPosition_id(positions.getPosition_id());
//            jobSelectionProcess.setSelection_procedure(""); // Default value if not set
//        } else {
//            positionsDTO.setSelection_procedure(jobSelectionProcess.getSelection_procedure());
//        }
//
//
//        return positionsDTO;
//    }
//
//
//
//
//    public List<JobPositionsDTO> getAllPositions() {
//        List<Positions> positionsList = jobPositionsRepository.findAll();
//        List<JobApplicationFee> jobApplicationFees = jobApplicationFeeRepository.findAll();
//        List<JobVacancies> jobVacanciesList = jobVacanciesRepository.findAll();
//        List<JobAgeRelaxations> jobAgeRelaxationsList = jobAgeRelaxationsRepository.findAll();
//        List<JobPostingLocation> jobPostingLocations = jobPostingLocationRepository.findAll();
//        List<JobSelectionProcess> jobSelectionProcesses = jobSelectionProcessRepository.findAll();
//
//        return positionsList.stream().filter(positions -> "Active".equals(positions.getPosition_status())).map(positions -> {
//            JobPositionsDTO positionsDTO = setJobPositionsDTO(
//                    positions,
//                    jobPostingLocations.stream()
//                            .filter(loc -> positions.getPosition_id().equals(loc.getPosition_id()))
//                            .findFirst()
//                            .orElse(null),
//                    jobApplicationFees.stream()
//                            .filter(fee -> positions.getPosition_id().equals(fee.getPosition_id()))
//                            .findFirst()
//                            .orElse(null),
//                    jobVacanciesList.stream()
//                            .filter(vacancy -> positions.getPosition_id().equals(vacancy.getPosition_id()))
//                            .findFirst()
//                            .orElse(null),
//                    jobAgeRelaxationsList.stream()
//                            .filter(relaxation -> positions.getPosition_id().equals(relaxation.getPosition_id()))
//                            .findFirst()
//                            .orElse(null),
//                    jobSelectionProcesses.stream()
//                            .filter(process -> positions.getPosition_id().equals(process.getPosition_id()))
//                            .findFirst()
//                            .orElse(null)
//            );
//            return positionsDTO;
//        }).toList();
//    }
//
//    public List<JobPositionsDTO> CreateBulkPositions(List<JobPositionsDTO> positionsDTOList) {
//        List<JobPositionsDTO> existingPositions = new ArrayList<>();
//        for (JobPositionsDTO positionsDTO : positionsDTOList) {
//            existingPositions.add(createPositions(positionsDTO));
//        }
//        return existingPositions;
//    }
//    public List<Positions> getall(){
//
//
//        return  jobPositionsRepository.findAll();
//    }
//    public String deletePosition(UUID position_id){
//        if(jobPositionsRepository.existsById(position_id)){
//            Positions pos = jobPositionsRepository.findById(position_id).orElse(null);
//            pos.setPosition_status("close");
//            jobPositionsRepository.save(pos);
//            return "Position deleted successfully";
//        }else{
//            return "Position not found";
//        }
//    }
//
//    public List<JobPositionsDTO> findByRequisitionId(UUID requisition_id) {
//        List<Positions> positionsList = jobPositionsRepository.findAllByRequisitionId(requisition_id);
//        List<JobApplicationFee> jobApplicationFees = jobApplicationFeeRepository.findAll();
//        List<JobVacancies> jobVacanciesList = jobVacanciesRepository.findAll();
//        List<JobAgeRelaxations> jobAgeRelaxationsList = jobAgeRelaxationsRepository.findAll();
//        List<JobPostingLocation> jobPostingLocations = jobPostingLocationRepository.findAll();
//        List<JobSelectionProcess> jobSelectionProcesses = jobSelectionProcessRepository.findAll();
//
//        return positionsList.stream().map(positions -> {
//            JobPositionsDTO positionsDTO = setJobPositionsDTO(
//                    positions,
//                    jobPostingLocations.stream()
//                            .filter(loc -> positions.getPosition_id().equals(loc.getPosition_id()))
//                            .findFirst()
//                            .orElse(null),
//                    jobApplicationFees.stream()
//                            .filter(fee -> positions.getPosition_id().equals(fee.getPosition_id()))
//                            .findFirst()
//                            .orElse(null),
//                    jobVacanciesList.stream()
//                            .filter(vacancy -> positions.getPosition_id().equals(vacancy.getPosition_id()))
//                            .findFirst()
//                            .orElse(null),
//                    jobAgeRelaxationsList.stream()
//                            .filter(relaxation -> positions.getPosition_id().equals(relaxation.getPosition_id()))
//                            .findFirst()
//                            .orElse(null),
//                    jobSelectionProcesses.stream()
//                            .filter(process -> positions.getPosition_id().equals(process.getPosition_id()))
//                            .findFirst()
//                            .orElse(null)
//            );
//            return positionsDTO;
//        }).toList();
//    }
//
//    public List<Positions> getAllByReqId(UUID requisition_id){
//        return jobPositionsRepository.findAllByRequisitionId(requisition_id);
//    }
//}
