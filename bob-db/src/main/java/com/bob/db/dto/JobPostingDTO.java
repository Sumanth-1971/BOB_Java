package com.bob.db.dto;

import java.util.List;
import java.util.UUID;

public class JobPostingDTO {
    private List<UUID> requisition_id;
    private List<String> job_postings;
    private String approval_status;
    private String user_id;

    public JobPostingDTO() {
    }
    public JobPostingDTO(List<UUID> requisition_id, List<String> job_postings, String approval_status) {
        this.requisition_id = requisition_id;
        this.job_postings = job_postings;
        this.approval_status = approval_status;
    }
    public List<UUID> getRequisition_id() {
        return requisition_id;
    }
    public void setRequisition_id(List<UUID> requisition_id) {
        this.requisition_id = requisition_id;
    }
    public List<String> getJob_postings() {
        return job_postings;
    }
    public void setJob_postings(List<String> job_postings) {
        this.job_postings = job_postings;
    }
    public String getApproval_status() {
        return approval_status;
    }
    public void setApproval_status(String approval_status) {
        this.approval_status = approval_status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}