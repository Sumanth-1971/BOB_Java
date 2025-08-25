package com.bob.JobCreation.controller;

import com.bob.JobCreation.service.JobRequisitionsService;
import com.bob.db.dto.ApiResponse;
import com.bob.db.dto.JobPostingDTO;
import com.bob.db.entity.JobRequisitionsEntity;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class JobRequisitionsController {
    @Autowired
    JobRequisitionsService jobRequisitionsService;


    @GetMapping("/getreq")
    public ResponseEntity<ApiResponse<List<com.bob.JobCreation.dto.JobRequisitionDTO>>> getAll() {
        List<com.bob.JobCreation.dto.JobRequisitionDTO> jobRequisitionsList = jobRequisitionsService.getAll();

        if (jobRequisitionsList.isEmpty()) {
            ApiResponse<List<com.bob.JobCreation.dto.JobRequisitionDTO>> apiResponse = new ApiResponse<>(
                    false,
                    "No job requisitions found",
                    jobRequisitionsList
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        ApiResponse<List<com.bob.JobCreation.dto.JobRequisitionDTO>> apiResponse = new ApiResponse<>(
                true,
                "Active jobs found",
                jobRequisitionsList
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getByStatus/{requisitionStatus}")
    @Description("Get job requisitions by status")
    public ResponseEntity<ApiResponse<List<JobRequisitionsEntity>>> getByStatus(@PathVariable String requisitionStatus) {

        List<JobRequisitionsEntity> jobRequisitionsList = jobRequisitionsService.findByRequisitionStatus( requisitionStatus);

        if (jobRequisitionsList.isEmpty()) {
            ApiResponse<List<JobRequisitionsEntity>> apiResponse = new ApiResponse<>(
                    false,
                    "No job requisitions found with status: " +  requisitionStatus,
                    jobRequisitionsList
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        ApiResponse<List<JobRequisitionsEntity>> apiResponse = new ApiResponse<>(
                true,
                "Jobs with status: " + requisitionStatus,
                jobRequisitionsList
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/create_requisitions")
    public ResponseEntity<ApiResponse<JobRequisitionsEntity>> createRequisitions(@RequestBody JobRequisitionsEntity jobRequisitions){
        try{
            if (checkFields(jobRequisitions)) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>(false, "All fields are required", null));
            }

            JobRequisitionsEntity jobRequisitionsres = jobRequisitionsService.createRequisitions(jobRequisitions);
            ApiResponse<JobRequisitionsEntity> apiResponse =new ApiResponse<>(true,"Created Successful", jobRequisitionsres);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED) ;
        }catch (Exception e){
            ApiResponse<JobRequisitionsEntity> apiResponse =new ApiResponse<>(false,"Failed to create"+e.getMessage(),null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST) ;
        }

    }

    @PostMapping("/create_bulk_requisitions")
    public ResponseEntity<?> createBulkRequistions(@RequestBody List<JobRequisitionsEntity> requisitionsList){
        try {
            if (requisitionsList == null || requisitionsList.isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>(false, "Requisitions list cannot be empty", null));
            }

            for (JobRequisitionsEntity jobRequisitions : requisitionsList) {
                if (checkFields(jobRequisitions)) {
                    return ResponseEntity
                            .badRequest()
                            .body(new ApiResponse<>(false, "All fields are required for each requisition", null));
                }
            }

            List<JobRequisitionsEntity> createdRequisitions = jobRequisitionsService.createBulkRequistions(requisitionsList);
            ApiResponse<List<JobRequisitionsEntity>> apiResponse = new ApiResponse<>(true, "Bulk Requisitions Created Successfully", createdRequisitions);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<List<JobRequisitionsEntity>> apiResponse = new ApiResponse<>(false, "Failed to create bulk requisitions: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_requisitions")
    public ResponseEntity<ApiResponse<JobRequisitionsEntity>> updateRequisitions(@RequestBody JobRequisitionsEntity jobRequisitions){
        try{

            if (checkFields(jobRequisitions)) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>(false, "All fields are required", null));
            }
            if(jobRequisitionsService.existsById(jobRequisitions.getRequisitionId()) == false){
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>(false, "Requisition ID does not exist", null));
            }
            JobRequisitionsEntity jobRequisitionsres =jobRequisitionsService.updateRequisitions(jobRequisitions);
            ApiResponse<JobRequisitionsEntity> apiResponse =new ApiResponse<>(true,"Updated Successful", jobRequisitionsres);
            return  new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<JobRequisitionsEntity> apiResponse =new ApiResponse<>(false,"Failed to Update",null);
            return  new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/active_requisitions")
    public ResponseEntity<ApiResponse<?>>  getActiveRequisitions() {
        List<JobRequisitionsEntity> activeJobs = jobRequisitionsService.getActiveRequisitions();
        if (activeJobs.isEmpty()) {
            ApiResponse<List<JobRequisitionsEntity>> apiResponse = new ApiResponse<>(
                    false,
                    "No active jobs found",
                    activeJobs
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        ApiResponse<List<JobRequisitionsEntity>> apiResponse = new ApiResponse<>(
                true,
                "Active jobs found",
                activeJobs
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete_requisitions/{requisition_id}")
    public ResponseEntity<ApiResponse<?>> deleteRequisitions(@PathVariable UUID requisition_id) {
        try {
            String res = jobRequisitionsService.deleteRequisitions(requisition_id);
            ApiResponse<JobRequisitionsEntity> apiResponse =new ApiResponse<>(true,"Delete Successful",null);
            return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<JobRequisitionsEntity> apiResponse =new ApiResponse<>(false,"Failed to Delete",null);
            return  new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
        }
    }

    private boolean checkFields(JobRequisitionsEntity jobRequisitions) {
        return jobRequisitions.getRequisitionTitle() == null || jobRequisitions.getRequisitionTitle().trim().isEmpty()
                || jobRequisitions.getRequisitionDescription() == null || jobRequisitions.getRequisitionDescription().trim().isEmpty()
                || jobRequisitions.getRegistrationStartDate() == null
                || jobRequisitions.getRegistrationEndDate() == null
                || jobRequisitions.getNoOfPositions() <= 0;
    }

    //job posting
    @PostMapping("/job_postings")
    public ResponseEntity<ApiResponse<?>> createJobPostings(@RequestBody JobPostingDTO jobPostings) {
        try {
            if (jobPostings == null || jobPostings.getJob_postings() == null || jobPostings.getJob_postings().isEmpty() || jobPostings.getRequisition_id() == null || jobPostings.getRequisition_id().isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>(false, "Job postings list cannot be empty", null));
            }

            String response = jobRequisitionsService.createJobPostings(jobPostings);

            ApiResponse<String> apiResponse = new ApiResponse<>(true, "Job postings created successfully", response);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<String> apiResponse = new ApiResponse<>(false, "Failed to create job postings: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/approve_job_postings")
    public ResponseEntity<ApiResponse<?>> updateJobPostings(@RequestBody com.bob.JobCreation.dto.JobPostingUpdateDTO jobPostings) {
        try {
            if (jobPostings == null || jobPostings.getRequisitionId() == null || jobPostings.getStatus() == null || jobPostings.getRole() == null){
                return ResponseEntity
                        .badRequest()
                        .body(new ApiResponse<>(false, "Job postings list cannot be empty", null));
            }

            String response = jobRequisitionsService.updateJobPostings(jobPostings);

            ApiResponse<String> apiResponse = new ApiResponse<>(true, "Job postings updated successfully", response);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> apiResponse = new ApiResponse<>(false, "Failed to update job postings: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/need_approval/{role}")
    public ResponseEntity<ApiResponse<List<JobRequisitionsEntity>>> getJobPostingsNeedApproval(@PathVariable String role) {
        try {
            List<JobRequisitionsEntity> jobRequisitionsList = jobRequisitionsService.getJobPostingsNeedApproval(role);

            if (jobRequisitionsList.isEmpty()) {
                ApiResponse<List<JobRequisitionsEntity>> apiResponse = new ApiResponse<>(
                        false,
                        "No job postings found that need approval for role: " + role,
                        jobRequisitionsList
                );
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }

            ApiResponse<List<JobRequisitionsEntity>> apiResponse = new ApiResponse<>(
                    true,
                    "Job postings found that need approval for role: " + role,
                    jobRequisitionsList
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<List<JobRequisitionsEntity>> apiResponse = new ApiResponse<>(false, "Failed to retrieve job postings: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

}