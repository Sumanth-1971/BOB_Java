package com.bob.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "job_requisitions")
public class PostJobRequisitions {
    @Id
    private UUID requisition_id;

    private String requisition_approval;

    private String updated_by;
    private LocalDateTime updated_date;
    private String others;

    private String job_postings;

    public PostJobRequisitions(UUID requisition_id,  String requisition_approval, String updated_by, LocalDateTime updated_date, String others, String job_postings) {
        this.requisition_id = requisition_id;

        this.requisition_approval = requisition_approval;
        this.updated_by = updated_by;
        this.updated_date = updated_date;
        this.others = others;
        this.job_postings = job_postings;
    }

    public PostJobRequisitions() {
    }
    public UUID getRequisition_id() {
        return requisition_id;
    }

    public void setRequisition_id(UUID requisition_id) {
        this.requisition_id = requisition_id;
    }


    public String getRequisition_approval() {
        return requisition_approval;
    }

    public void setRequisition_approval(String requisition_approval) {
        this.requisition_approval = requisition_approval;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDateTime updated_date) {
        this.updated_date = updated_date;
    }


    public String getJob_postings() {
        return job_postings;
    }

    public void setJob_postings(String job_postings) {
        this.job_postings = job_postings;
    }


    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
