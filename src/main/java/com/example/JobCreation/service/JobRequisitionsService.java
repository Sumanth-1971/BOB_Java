package com.example.JobCreation.service;

import com.example.JobCreation.dto.JobPostingDTO;
import com.example.JobCreation.model.JobRequisitions;
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
    JobRequisitionsRepository jobRequisitionsRepository;

    public List<JobRequisitions>  getAll(){
        return jobRequisitionsRepository.findAll();
    }

    public List<JobRequisitions> findByRequisitionStatus(String requisitionStatus){
        return jobRequisitionsRepository.findByRequisitionStatus(requisitionStatus);
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
            jobRequisitionsRepository.deleteById(id);
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
                        jobRequisitions.setRequisition_approval("Published");
                        jobRequisitionsRepository.save(jobRequisitions);
                    }
                }
            }
            return "Job postings created successfully " ;
        }catch (Exception e) {
            throw new Exception("Failed to create job postings: " + e.getMessage());
        }


    }
    public boolean existsById(UUID id) {
        return jobRequisitionsRepository.existsById(id);
    }

    public List<JobRequisitions> getActiveRequisitions() {
        LocalDate today = LocalDate.now();
        return jobRequisitionsRepository.findAll().stream()
                .filter(job -> job.getRequisition_status().equals("Published") || job.getRequisition_status().equals("Approved")).toList();
    }
}