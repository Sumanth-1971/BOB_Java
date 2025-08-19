package com.bob.JobCreation.service;

import com.bob.JobCreation.model.UserEntity;
import com.bob.JobCreation.model.WorkflowApprovalEntity;
import com.bob.JobCreation.repository.AuditTrailRepository;
import com.bob.JobCreation.dto.JobPostingDTO;
import com.bob.JobCreation.model.AuditTrailEntity;
import com.bob.JobCreation.model.JobRequisitions;
import com.bob.JobCreation.repository.JobRequisitionsRepository;
import com.bob.JobCreation.repository.UserRepository;
import com.bob.JobCreation.repository.WorkflowApprovalEntityRepository;
import com.bob.JobCreation.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobRequisitionsService {

    @Autowired
    JobRequisitionsRepository jobRequisitionsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkflowApprovalEntityRepository workflowApprovalEntityRepository;

    @Autowired
    AuditTrailRepository auditTrailRepository;

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
            String externalJobPostings = jobPostings.getJob_postings().stream().collect(Collectors.joining(","));
            if( jobPostings.getApproval_status().equals("Direct Approval") ) {
                for (UUID jobRequisitionsId : jobPostings.getRequisition_id()) {
                    JobRequisitions jobRequisitions = jobRequisitionsRepository.findById(jobRequisitionsId).orElse(null);
                    if (jobRequisitions != null) {
                        jobRequisitions.setJob_postings(externalJobPostings);
                        jobRequisitions.setRequisition_status("Approved");
                        jobRequisitions.setRequisition_approval("Direct Approval");
                        jobRequisitionsRepository.save(jobRequisitions);
                    }
                }
            } else if (jobPostings.getApproval_status().equals(AppConstants.APPROVAL_STATUS_WORKFLOW) ){
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

                        jobRequisitionsRepository.save(jobRequisition);
                    }
                }
            }
            return "Job postings created successfully ";
        }catch (Exception e) {
            throw new Exception("Failed to create job postings: " + e.getMessage());
        }


    }

    public String approvalSubmission(UUID requisitionId, String approvalStatus, String userId, String comments, String userRole) {
         Optional<JobRequisitions> jobRequisitions = jobRequisitionsRepository.findById(requisitionId);
        if(jobRequisitions.isPresent()){
            JobRequisitions jobRequisition = jobRequisitions.get();
            JobRequisitions oldJobRequisition = new JobRequisitions(jobRequisition); // Create a copy for audit purposes

            String updateStatus = jobRequisition.getRequisition_status();
            if(!updateStatus.equals(AppConstants.REQ_PENDING_APPROVAL_L1)
                    && !updateStatus.equals(AppConstants.REQ_PENDING_APPROVAL_L2)){
                return("Requisition is not in pending approval status for L1 or L2");
            }
            if(!jobRequisition.getRequisition_approval().equals(AppConstants.APPROVAL_STATUS_WORKFLOW)){
                return("Requisition is not in workflow approval status");
            }

            if(approvalStatus.equals(AppConstants.API_APPROVAL_APPROVED)) {         //Approved flow
                if (userRole.equals(AppConstants.L2)) {
                    //If L2 approves - direct approval
                    jobRequisition.setRequisition_status(AppConstants.REQ_APPROVAL_APPROVED);
                } else if (userRole.equals(AppConstants.L1) && updateStatus.equals(AppConstants.REQ_PENDING_APPROVAL_L1)){
                    //If L1 approves - next approval required
                    jobRequisition.setRequisition_status(AppConstants.REQ_PENDING_APPROVAL_L2);
                } else {
                    return("Invalid approval status or user role");
                }
            } else if (approvalStatus.equals(AppConstants.API_APPROVAL_DENIED)) {         //Denied flow
                if (userRole.equals(AppConstants.L1) || userRole.equals(AppConstants.L2)) {
                    //If L1 or L2 denies - set to denied status
                    jobRequisition.setRequisition_status(AppConstants.REQ_APPROVAL_REJECTED);
                } else {
                    return("Invalid user role for rejection");
                }
            } else {
                return("Invalid approval status");
            }

            jobRequisition.setRequisition_comments(comments);

            // Update the approval entity
            jobRequisitionsRepository.save(jobRequisition);

            // Update the workflow entity
            insertWorkflowApprovalEntity(jobRequisition,AppConstants.JOB_REQUISITIONS,userRole,userId,approvalStatus);

            // Update audit table
            insertAuditRecord(oldJobRequisition, jobRequisition, userId, approvalStatus);

            return "Requisition approval status updated successfully";
        }else {
            return ("Requisition not found with ID: " + requisitionId);
        }
    }

    public void insertWorkflowApprovalEntity(JobRequisitions jobRequisition, String entityType,String userRole, String userId,String approvalStatus) {
        WorkflowApprovalEntity workflowApprovalEntity = new WorkflowApprovalEntity();
        workflowApprovalEntity.setApprovalId(UUID.randomUUID());
        workflowApprovalEntity.setEntityType(entityType);
        workflowApprovalEntity.setEntityId(jobRequisition.getRequisition_id());
        workflowApprovalEntity.setStepNumber(jobRequisition.getRequisition_status().equals(AppConstants.REQ_APPROVAL_APPROVED)?2:1);
        workflowApprovalEntity.setApproverRole(userRole);
        workflowApprovalEntity.setApproverId(Integer.parseInt(userId));
        workflowApprovalEntity.setAction(approvalStatus);
        workflowApprovalEntity.setActionDate(LocalDateTime.now());
        workflowApprovalEntity.setComments(jobRequisition.getRequisition_comments());
        workflowApprovalEntity.setStatus(jobRequisition.getRequisition_status().equals(AppConstants.REQ_APPROVAL_APPROVED)?
                    AppConstants.WORKFLOW_STATUS_COMPLETED:AppConstants.WORKFLOW_STATUS_PENDING);
        workflowApprovalEntityRepository.save(workflowApprovalEntity);
    }

    public void insertAuditRecord(JobRequisitions oldJobRequisition, JobRequisitions jobRequisition, String userId, String approvalStatus) {

        AuditTrailEntity auditTrailEntity = new AuditTrailEntity();
        auditTrailEntity.setAuditId(UUID.randomUUID());
        auditTrailEntity.setEntityType(AppConstants.JOB_REQUISITIONS);
        auditTrailEntity.setEntityId(oldJobRequisition.getRequisition_id());
        auditTrailEntity.setFieldChanged(AppConstants.STATUS_FIELD);
        auditTrailEntity.setOldValue(oldJobRequisition.getRequisition_status());
        auditTrailEntity.setNewValue(jobRequisition.getRequisition_status());
        auditTrailEntity.setChangedBy(Long.parseLong(userId));
        auditTrailEntity.setChangeDate(LocalDateTime.now());
        auditTrailEntity.setChangeType(AppConstants.CHANGE_TYPE_UPDATE);
        auditTrailRepository.save(auditTrailEntity);

        System.out.println("Audit Record: User ID: " + userId + ", Requisition ID: " + jobRequisition.getRequisition_id() +
                ", Approval Status: " + approvalStatus + ", Timestamp: " + LocalDateTime.now());
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