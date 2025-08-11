package com.example.CandidateDetails.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidate_applications")
public class CandidateApplications {
    @Id
    private UUID application_id;

    private UUID candidate_id;

    private UUID position_id;

    private String application_status;

    private LocalDateTime application_date;

    private LocalDateTime updated_date;

    public CandidateApplications() {
    }

    public CandidateApplications(UUID application_id, UUID candidate_id, UUID position_id, String application_status, LocalDateTime application_date, LocalDateTime updated_date) {
        this.application_id = application_id;
        this.candidate_id = candidate_id;
        this.position_id = position_id;
        this.application_status = application_status;
        this.application_date = application_date;
        this.updated_date = updated_date;
    }

    public UUID getApplication_id() {
        return application_id;
    }

    public void setApplication_id(UUID application_id) {
        this.application_id = application_id;
    }

    public UUID getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(UUID candidate_id) {
        this.candidate_id = candidate_id;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
    }

    public LocalDateTime getApplication_date() {
        return application_date;
    }

    public void setApplication_date(LocalDateTime application_date) {
        this.application_date = application_date;
    }

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDateTime updated_date) {
        this.updated_date = updated_date;
    }
}
