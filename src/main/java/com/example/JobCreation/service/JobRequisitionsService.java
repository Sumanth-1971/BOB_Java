package com.example.JobCreation.service;

import com.example.JobCreation.dto.JobPositionsDTO;
import com.example.JobCreation.dto.JobPostingDTO;
import com.example.JobCreation.dto.JobPostingUpdateDTO;
import com.example.JobCreation.dto.JobRequisitionDTO;
import com.example.JobCreation.model.JobRequisitions;
import com.example.JobCreation.repository.JobPositionsRepository;
import com.example.JobCreation.repository.JobRequisitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobRequisitionsService {

    @Autowired
    private JobRequisitionsRepository jobRequisitionsRepository;
    @Autowired
    private JobPositionsRepository jobPositionsRepository;

    public List<JobRequisitionDTO>  getAll(){
        List<JobRequisitions> jobRequisitions =  jobRequisitionsRepository.findAll().stream().filter(
                jobRequisition -> jobRequisition.getIsactive() == 1).collect(Collectors.toList());
        if (jobRequisitions.isEmpty()) {
            return null;
        }
        List<JobRequisitionDTO> jobRequisitionDTOS = jobRequisitions.stream()
                .map(jobRequisition -> {
                    JobRequisitionDTO dto = new JobRequisitionDTO(
                            jobRequisition.getRequisition_id(),
                            jobRequisition.getRequisition_code(),
                            jobRequisition.getRequisition_title(),
                            jobRequisition.getRequisition_description(),
                            jobRequisition.getRegistration_start_date(),
                            jobRequisition.getRegistration_end_date(),
                            jobRequisition.getRequisition_status(),
                            jobRequisition.getRequisition_comments(),
                            jobRequisition.getRequisition_approval(),
                            jobRequisition.getNo_of_positions(),
                            jobRequisition.getJob_postings(),
                            jobRequisition.getRequisition_approval_notes()
                    );

                    // set count separately
                    dto.setCount(
                            jobPositionsRepository.findAllByRequisitionId(jobRequisition.getRequisition_id()).size()
                    );

                    return dto;
                })
                .collect(Collectors.toList());

        return jobRequisitionDTOS;
    }

    public List<JobRequisitions> findByRequisitionStatus(String requisitionStatus){
        return jobRequisitionsRepository.findByRequisitionStatus(requisitionStatus)
                .stream().filter(jobRequisition -> jobRequisition.getIsactive()==1).collect(Collectors.toList());
    }

    public JobRequisitions createRequisitions(JobRequisitions jobRequisitions){
        jobRequisitions.setRequisition_code("JREQ-" +(1000+ jobRequisitionsRepository.count()));
        jobRequisitions.setRequisition_id(UUID.randomUUID());
        jobRequisitions.setRequisition_status("Submitted");
        LocalDateTime tme =LocalDateTime.now();
        jobRequisitions.setCreated_date(tme);
        jobRequisitions.setUpdated_date(tme);
        return jobRequisitionsRepository.save(jobRequisitions);
    }
    public List<JobRequisitions> createBulkRequistions(List<JobRequisitions> jobRequisitionsList) {
        LocalDateTime tme = LocalDateTime.now();
        for (JobRequisitions jobRequisitions : jobRequisitionsList) {
            jobRequisitions.setRequisition_code("JREQ-" + (1000 + jobRequisitionsRepository.count()));
            jobRequisitions.setRequisition_id(UUID.randomUUID());
            jobRequisitions.setRequisition_status("Submitted");
            jobRequisitions.setCreated_date(tme);
            jobRequisitions.setUpdated_date(tme);
        }
        return jobRequisitionsRepository.saveAll(jobRequisitionsList);
    }
    public JobRequisitions updateRequisitions(JobRequisitions jobRequisitions){
        JobRequisitions jobRequisitions1 = jobRequisitionsRepository.findById(jobRequisitions.getRequisition_id())
                .orElseThrow(() -> new RuntimeException("Requisition not found with ID: " + jobRequisitions.getRequisition_id()));
        jobRequisitions1.setRequisition_id(jobRequisitions.getRequisition_id());

        jobRequisitions1.setRequisition_title(jobRequisitions.getRequisition_title());
        jobRequisitions1.setRequisition_description(jobRequisitions.getRequisition_description());

        jobRequisitions1.setRegistration_start_date(jobRequisitions.getRegistration_start_date());
        jobRequisitions1.setRegistration_end_date(jobRequisitions.getRegistration_end_date());
        jobRequisitions1.setRequisition_comments(jobRequisitions.getRequisition_comments());
        jobRequisitions1.setNo_of_positions(jobRequisitions.getNo_of_positions());


        jobRequisitions.setUpdated_date(LocalDateTime.now());
        return jobRequisitionsRepository.save(jobRequisitions);
    }
    public String deleteRequisitions(UUID id) {
        try {
            if (!jobRequisitionsRepository.existsById(id)) {
                return "Requisition not found";
            }
            //SOFT DELETING
            JobRequisitions jobRequisitions = jobRequisitionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Requisition not found with ID: " + id));
            jobRequisitions.setIsactive(0);
            jobRequisitions.setUpdated_date(LocalDateTime.now());
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
                    JobRequisitions jobRequisitions = jobRequisitionsRepository.findById(jobRequisitionsId).orElse(null);
                    if (jobRequisitions != null) {
                        jobRequisitions.setJob_postings(result);
                        jobRequisitions.setRequisition_status("Approved");
                        jobRequisitions.setRequisition_approval("Direct Approval");
                        jobRequisitionsRepository.save(jobRequisitions);
                    }
                }
            } else if (jobPostings.getApproval_status().equals("Workflow") ){
                for (UUID jobRequisitionsId : jobPostings.getRequisition_id()) {
                    JobRequisitions jobRequisitions = jobRequisitionsRepository.findById(jobRequisitionsId).orElse(null);
                    if (jobRequisitions != null) {
                        jobRequisitions.setJob_postings(result);
                        jobRequisitions.setRequisition_status("Pending L1 Approval");
                        jobRequisitions.setRequisition_approval("Workflow");
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
        JobRequisitions jobRequisitions = jobRequisitionsRepository.findById(id).orElse(null);
        return (jobRequisitions != null && jobRequisitions.getIsactive() == 1)? true :false;
    }
    public List<JobRequisitions> getActiveRequisitions() {
        LocalDate today = LocalDate.now();
        return jobRequisitionsRepository.findAll().stream()
                .filter(job -> job.getIsactive()==1 && (job.getRequisition_status().equals("Published") || job.getRequisition_status().equals("Approved"))).toList();
    }

    public String updateJobPostings(JobPostingUpdateDTO jobPostings) {
        try {
            for (UUID jobRequisitionsId : jobPostings.getRequisitionId()) {
                if (!jobRequisitionsRepository.existsById(jobRequisitionsId)) {
                    return "Requisition with ID " + jobRequisitionsId + " does not exist.";
                }
            }

            for (UUID jobRequisitionsId : jobPostings.getRequisitionId()) {
                JobRequisitions jobRequisitions = jobRequisitionsRepository.findById(jobRequisitionsId).orElse(null);
                if (jobRequisitions != null && jobRequisitions.getIsactive() == 1) {
                    if (jobPostings.getStatus().equalsIgnoreCase("Rejected")) {
                        // Rejecting the job posting
                        jobRequisitions.setRequisition_status("Rejected");
                        jobRequisitions.setUpdated_by(jobPostings.getUserId());
                        jobRequisitions.setUpdated_date(LocalDateTime.now());
                        jobRequisitions.setRequisition_approval_notes(jobPostings.getDescription());

                    } else if (jobPostings.getStatus().equalsIgnoreCase("Approved") && jobPostings.getRole().equals("L1")) {
                        // Updating the job posting for L1 approval
                        jobRequisitions.setRequisition_status("Pending L2 Approval");
                        jobRequisitions.setUpdated_by(jobPostings.getUserId());
                        jobRequisitions.setUpdated_date(LocalDateTime.now());
                        jobRequisitions.setRequisition_approval_notes(jobPostings.getDescription());

                    } else if (jobPostings.getStatus().equalsIgnoreCase("Approved") && jobPostings.getRole().equals("L2")) {
                        // Updating the job posting for L2 approval
                        jobRequisitions.setRequisition_status("Approved");
                        jobRequisitions.setUpdated_by(jobPostings.getUserId());
                        jobRequisitions.setUpdated_date(LocalDateTime.now());
                        jobRequisitions.setRequisition_approval_notes(jobPostings.getDescription());
                    }else {
                        throw new RuntimeException("Invalid status or role provided for job posting update.");
                    }
                    jobRequisitions.setUpdated_by(jobPostings.getUserId());
                    jobRequisitions.setUpdated_date(LocalDateTime.now());
                    jobRequisitionsRepository.save(jobRequisitions);
                }
            }
            return "Job postings updated successfully";
        } catch (Exception e) {
            return "Failed to update job postings: " + e.getMessage();
        }
    }

    public List<JobRequisitions> getJobPostingsNeedApproval(String role) {
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