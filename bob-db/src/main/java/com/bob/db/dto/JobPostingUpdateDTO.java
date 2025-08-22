package com.bob.JobCreation.dto;

import java.util.List;
import java.util.UUID;

public class JobPostingUpdateDTO {
    private String role;
    private List<UUID> requisitionId;

    private String UserId;
    private String status;
    private String description;

    public JobPostingUpdateDTO() {
    }

    public JobPostingUpdateDTO(String role, UUID requisitionId, String userId, String status, String description) {
        this.role = role;
        this.requisitionId = List.of(requisitionId);
        UserId = userId;
        this.status = status;
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UUID> getRequisitionId() {
        return requisitionId;
    }
    public void setRequisitionId(List<UUID> requisitionId) {
        this.requisitionId = requisitionId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
