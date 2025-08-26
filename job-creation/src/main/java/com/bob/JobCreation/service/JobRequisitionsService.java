package com.bob.JobCreation.service;

import com.bob.JobCreation.dto.JobPostingUpdateDTO;
import com.bob.JobCreation.dto.JobRequisitionDTO;
import com.bob.db.dto.JobPositionsDTO;
import com.bob.db.dto.JobPostingDTO;
import com.bob.db.entity.JobRequisitionsEntity;
import com.bob.db.repository.JobRequisitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobRequisitionsService {

    private final JobPositionService jobPositionService;

    public JobRequisitionsService(@Lazy JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }
    @Autowired
    private JobRequisitionsRepository jobRequisitionsRepository;
    public List<JobRequisitionDTO>  getAll(){
        List<JobRequisitionsEntity> jobRequisitions =  jobRequisitionsRepository.findAll().stream().filter(
                jobRequisition -> jobRequisition.getIsactive() == 1).collect(Collectors.toList());
        if (jobRequisitions.isEmpty()) {
            return null;
        }
        List<JobRequisitionDTO> jobRequisitionDTOS = jobRequisitions.stream()
                .map(jobRequisition -> {
                    JobRequisitionDTO dto = new JobRequisitionDTO(
                            jobRequisition.getRequisitionId(),
                            jobRequisition.getRequisitionCode(),
                            jobRequisition.getRequisitionTitle(),
                            jobRequisition.getRequisitionDescription(),
                            jobRequisition.getRegistrationStartDate(),
                            jobRequisition.getRegistrationEndDate(),
                            jobRequisition.getRequisitionStatus(),
                            jobRequisition.getRequisitionComments(),
                            jobRequisition.getRequisitionApproval(),
                            jobRequisition.getNoOfPositions(),
                            jobRequisition.getJobPostings(),
                            jobRequisition.getRequisitionApprovalNotes()
                    );

                    // set count separately
                    dto.setCount(
                     jobPositionService.findByReqId(jobRequisition.getRequisitionId())
                            .stream()
                            .mapToInt(JobPositionsDTO::getNo_of_vacancies) // convert to IntStream
                            .sum() // sum the no_of_vacancies
                    );

                    return dto;
                })
                .collect(Collectors.toList());

        return jobRequisitionDTOS;
    }

    public List<JobRequisitionsEntity> findByRequisitionStatus(String requisitionStatus){
        return jobRequisitionsRepository.findByRequisitionStatus(requisitionStatus)
                .stream().filter(jobRequisition -> jobRequisition.getIsactive()==1).collect(Collectors.toList());
    }

    public JobRequisitionsEntity createRequisitions(JobRequisitionsEntity jobRequisitions){
        jobRequisitions.setRequisitionCode("JREQ-" +(1000+ jobRequisitionsRepository.count()));
        jobRequisitions.setRequisitionId(UUID.randomUUID());
        jobRequisitions.setRequisitionStatus("Submitted");
        LocalDateTime tme =LocalDateTime.now();
        jobRequisitions.setCreatedDate(tme);
        jobRequisitions.setUpdatedDate(tme);
        return jobRequisitionsRepository.save(jobRequisitions);
    }
    public List<JobRequisitionsEntity> createBulkRequistions(List<JobRequisitionsEntity> jobRequisitionsList) {
        LocalDateTime tme = LocalDateTime.now();
        for (JobRequisitionsEntity jobRequisitions : jobRequisitionsList) {
            jobRequisitions.setRequisitionCode("JREQ-" + (1000 + jobRequisitionsRepository.count()));
            jobRequisitions.setRequisitionId(UUID.randomUUID());
            jobRequisitions.setRequisitionStatus("Submitted");
            jobRequisitions.setCreatedDate(tme);
            jobRequisitions.setUpdatedDate(tme);
        }
        return jobRequisitionsRepository.saveAll(jobRequisitionsList);
    }
    public JobRequisitionsEntity updateRequisitions(JobRequisitionsEntity jobRequisitions){
        JobRequisitionsEntity jobRequisitions1 = jobRequisitionsRepository.findById(jobRequisitions.getRequisitionId())
                .orElseThrow(() -> new RuntimeException("Requisition not found with ID: " + jobRequisitions.getRequisitionId()));
        jobRequisitions1.setRequisitionId(jobRequisitions.getRequisitionId());

        jobRequisitions1.setRequisitionTitle(jobRequisitions.getRequisitionTitle());
        jobRequisitions1.setRequisitionDescription(jobRequisitions.getRequisitionDescription());

        jobRequisitions1.setRegistrationStartDate(jobRequisitions.getRegistrationStartDate());
        jobRequisitions1.setRegistrationEndDate(jobRequisitions.getRegistrationEndDate());
        jobRequisitions1.setRequisitionComments(jobRequisitions.getRequisitionComments());
        jobRequisitions1.setNoOfPositions(jobRequisitions.getNoOfPositions());


        jobRequisitions.setUpdatedDate(LocalDateTime.now());
        return jobRequisitionsRepository.save(jobRequisitions);
    }
    public String deleteRequisitions(UUID id) {
        try {
            if (!jobRequisitionsRepository.existsById(id)) {
                return "Requisition not found";
            }
            //SOFT DELETING
            JobRequisitionsEntity jobRequisitions = jobRequisitionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Requisition not found with ID: " + id));
            jobRequisitions.setIsactive(0);
            jobRequisitions.setUpdatedDate(LocalDateTime.now());
            jobRequisitionsRepository.save(jobRequisitions);
            //jobRequisitionsRepository.deleteById(id);
            return "Deleted Successful";
        }catch (Exception e){
            return "Deleted Unsuccessful"+e.getMessage() ;
        }
    }
    public String createJobPostings(JobPostingDTO jobPostings) throws Exception {
        try {
            for (UUID jobRequisitionsId : jobPostings.getRequisition_id()) {
                if (!jobRequisitionsRepository.existsById(jobRequisitionsId)) {
                    return "Requisition with ID " + jobRequisitionsId + " does not exist.";
                }
            }

            String result = jobPostings.getJob_postings().stream().collect(Collectors.joining(","));
            if( jobPostings.getApproval_status().equals("Direct Approval") ) {
                for (UUID jobRequisitionsId : jobPostings.getRequisition_id()) {
                    JobRequisitionsEntity jobRequisitions = jobRequisitionsRepository.findById(jobRequisitionsId).orElse(null);
                    if (jobRequisitions != null) {
                        jobRequisitions.setJobPostings(result);
                        jobRequisitions.setRequisitionStatus("Approved");
                        jobRequisitions.setRequisitionApproval("Direct Approval");
                        jobRequisitionsRepository.save(jobRequisitions);
                    }
                }
            }
            /*else if (jobPostings.getApproval_status().equals(AppConstants.APPROVAL_STATUS_WORKFLOW) ){
                for (UUID jobRequisitionsId : jobPostings.getRequisition_id()) {
                    JobRequisitions jobRequisition = jobRequisitionsRepository.findById(jobRequisitionsId).orElse(null);
                    if (jobRequisition != null) {
                        jobRequisition.setJob_postings(externalJobPostings);

                        Optional<UserEntity> user = userRepository.findById(Long.parseLong(jobPostings.getUser_id()));
                        if (user.isPresent()) {
                            UserEntity currentUserEntity = user.get();
                            Long managerId = currentUserEntity.getManager_id();

                            //n+1 flow
                            if (managerId != null) {
                                Optional<UserEntity> manager = userRepository.findById(managerId);
                                if (manager.isPresent()) {
                                    jobRequisition.setRequisition_status(AppConstants.REQ_APPROVAL_PENDING.concat(AppConstants.UNDERSCORE).concat(manager.get().getRole()));
                                    jobRequisition.setRequisition_approval(AppConstants.APPROVAL_STATUS_WORKFLOW);
                                } else {
                                    throw new Exception("Manager User with ID " + managerId + " not found.");
                                }
                            } else {
                                //approval flow
                                jobRequisition.setRequisition_status(AppConstants.REQ_APPROVAL_APPROVED);
                                jobRequisition.setRequisition_approval(AppConstants.APPROVAL_STATUS_WORKFLOW);
                            }
                        } else {
                            throw new Exception("User with ID " + jobPostings.getUser_id() + " not found.");
                        }

                        jobRequisitionsRepository.save(jobRequisition);*/
            else if (jobPostings.getApproval_status().equals("Workflow") ){
                for (UUID jobRequisitionsId : jobPostings.getRequisition_id()) {
                    JobRequisitionsEntity jobRequisitions = jobRequisitionsRepository.findById(jobRequisitionsId).orElse(null);
                    if (jobRequisitions != null) {
                        jobRequisitions.setJobPostings(result);
                        jobRequisitions.setRequisitionStatus("Pending L1 Approval");
                        jobRequisitions.setRequisitionApproval("Workflow");
                        jobRequisitionsRepository.save(jobRequisitions);
                    }
                }
            }

            return "Job postings created successfully ";
        }catch (Exception e) {
            throw new Exception("Failed to create job postings: " + e.getMessage());
        }
    }
    public boolean existsById(UUID id) {
        // Check if a job requisition with the given ID exists
        if (id == null) {
            return false; // or throw an exception, depending on your design choice
        }
        JobRequisitionsEntity jobRequisitions = jobRequisitionsRepository.findById(id).orElse(null);
        return (jobRequisitions != null && jobRequisitions.getIsactive() == 1)? true :false;
    }
    public List<JobRequisitionsEntity> getActiveRequisitions() {
        LocalDate today = LocalDate.now();
        return jobRequisitionsRepository.findAll().stream()
                .filter(job -> job.getIsactive()==1 && (job.getRequisitionStatus().equals("Published") || job.getRequisitionStatus().equals("Approved"))).toList();
    }

    public String updateJobPostings(JobPostingUpdateDTO jobPostings) {
        try {
            for (UUID jobRequisitionsId : jobPostings.getRequisitionId()) {
                if (!jobRequisitionsRepository.existsById(jobRequisitionsId)) {
                    return "Requisition with ID " + jobRequisitionsId + " does not exist.";
                }
            }

            for (UUID jobRequisitionsId : jobPostings.getRequisitionId()) {
                JobRequisitionsEntity jobRequisitions = jobRequisitionsRepository.findById(jobRequisitionsId).orElse(null);
                if (jobRequisitions != null && jobRequisitions.getIsactive() == 1) {
                    if (jobPostings.getStatus().equalsIgnoreCase("Rejected")) {
                        // Rejecting the job posting
                        jobRequisitions.setRequisitionStatus("Rejected");
                        jobRequisitions.setUpdatedBy(jobPostings.getUserId());
                        jobRequisitions.setUpdatedDate(LocalDateTime.now());
                        jobRequisitions.setRequisitionApprovalNotes(jobPostings.getDescription());

                    } else if (jobPostings.getStatus().equalsIgnoreCase("Approved") && jobPostings.getRole().equals("L1")) {
                        // Updating the job posting for L1 approval
                        jobRequisitions.setRequisitionStatus("Pending L2 Approval");
                        jobRequisitions.setUpdatedBy(jobPostings.getUserId());
                        jobRequisitions.setUpdatedDate(LocalDateTime.now());
                        jobRequisitions.setRequisitionApprovalNotes(jobPostings.getDescription());

                    } else if (jobPostings.getStatus().equalsIgnoreCase("Approved") && jobPostings.getRole().equals("L2")) {
                        // Updating the job posting for L2 approval
                        jobRequisitions.setRequisitionStatus("Approved");
                        jobRequisitions.setUpdatedBy(jobPostings.getUserId());
                        jobRequisitions.setUpdatedDate(LocalDateTime.now());
                        jobRequisitions.setRequisitionApprovalNotes(jobPostings.getDescription());
                    }else {
                        throw new RuntimeException("Invalid status or role provided for job posting update.");
                    }
                    jobRequisitions.setUpdatedBy(jobPostings.getUserId());
                    jobRequisitions.setUpdatedDate(LocalDateTime.now());
                    jobRequisitionsRepository.save(jobRequisitions);
                }
            }
            return "Job postings updated successfully";
        } catch (Exception e) {
            return "Failed to update job postings: " + e.getMessage();
        }
    }

    public List<JobRequisitionsEntity> getJobPostingsNeedApproval(String role) {
        if (role.equals("L1")) {
            return jobRequisitionsRepository.findByRequisitionStatus("Pending L1 Approval").stream().filter(
                    jobRequisition -> jobRequisition.getIsactive() == 1).collect(Collectors.toList());
        } else if (role.equals("L2")) {
            return jobRequisitionsRepository.findByRequisitionStatus("Pending L2 Approval").stream()
                    .filter(jobRequisition -> jobRequisition.getIsactive() == 1).collect(Collectors.toList());
        } else {
            return List.of(); // Return an empty list for other roles
        }
    }

}