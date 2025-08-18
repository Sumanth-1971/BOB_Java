package com.example.JobCreation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "job_requisitions")
public class JobRequisitions {
    @Id
    private UUID requisition_id;
    private String requisition_code;
    private String requisition_title;
    @Column(name = "requisition_description", columnDefinition = "text")
    private String requisition_description;
    private LocalDate registration_start_date;
    private LocalDate registration_end_date;
    private String requisition_status;
    @Column(name = "requisition_comments", columnDefinition = "text")
    private String requisition_comments;
    private String requisition_approval;
    private String created_by;
    private LocalDateTime created_date;
    private String updated_by;
    private LocalDateTime updated_date;

    @Column(name = "others" , columnDefinition = "jsonb")
    private String others;
    private Integer no_of_positions;
    @Column(name = "job_postings", columnDefinition = "text")
    private String job_postings;

    @Column(name = "requisition_approval_notes", columnDefinition = "text")
    private String requisition_approval_notes;
    private Integer isactive = 1;

    public JobRequisitions(UUID requisition_id, String requisition_code, String requisition_title, String requisition_description, LocalDate registration_start_date, LocalDate registration_end_date, String requisition_status, String requisition_comments, String requisition_approval, String created_by, LocalDateTime created_date, String updated_by, LocalDateTime updated_date, String others, Integer no_of_positions, String job_postings, String requisition_approval_notes, Integer isactive) {
        this.requisition_id = requisition_id;
        this.requisition_code = requisition_code;
        this.requisition_title = requisition_title;
        this.requisition_description = requisition_description;
        this.registration_start_date = registration_start_date;
        this.registration_end_date = registration_end_date;
        this.requisition_status = requisition_status;
        this.requisition_comments = requisition_comments;
        this.requisition_approval = requisition_approval;
        this.created_by = created_by;
        this.created_date = created_date;
        this.updated_by = updated_by;
        this.updated_date = updated_date;
        this.others = others;
        this.no_of_positions = no_of_positions;
        this.job_postings = job_postings;
        this.requisition_approval_notes = requisition_approval_notes;
        this.isactive = isactive;
    }

    public JobRequisitions() {
    }
    public UUID getRequisition_id() {
        return requisition_id;
    }

    public void setRequisition_id(UUID requisition_id) {
        this.requisition_id = requisition_id;
    }

    public String getRequisition_code() {
        return requisition_code;
    }

    public void setRequisition_code(String requisition_code) {
        this.requisition_code = requisition_code;
    }

    public String getRequisition_title() {
        return requisition_title;
    }

    public void setRequisition_title(String requisition_title) {
        this.requisition_title = requisition_title;
    }

    public String getRequisition_description() {
        return requisition_description;
    }

    public void setRequisition_description(String requisition_description) {
        this.requisition_description = requisition_description;
    }


    public String getRequisition_status() {
        return requisition_status;
    }

    public void setRequisition_status(String requisition_status) {
        this.requisition_status = requisition_status;
    }

    public String getRequisition_comments() {
        return requisition_comments;
    }

    public void setRequisition_comments(String requisition_comments) {
        this.requisition_comments = requisition_comments;
    }

    public String getRequisition_approval() {
        return requisition_approval;
    }

    public void setRequisition_approval(String requisition_approval) {
        this.requisition_approval = requisition_approval;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
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


    public int getNo_of_positions() {
        return no_of_positions;
    }

    public void setNo_of_positions(int no_of_positions) {
        this.no_of_positions = no_of_positions;
    }

    public String getJob_postings() {
        return job_postings;
    }

    public void setJob_postings(String job_postings) {
        this.job_postings = job_postings;
    }

    public LocalDate getRegistration_start_date() {
        return registration_start_date;
    }

    public void setRegistration_start_date(LocalDate registration_start_date) {
        this.registration_start_date = registration_start_date;
    }

    public LocalDate getRegistration_end_date() {
        return registration_end_date;
    }

    public void setRegistration_end_date(LocalDate registration_end_date) {
        this.registration_end_date = registration_end_date;
    }

    public void setNo_of_positions(Integer no_of_positions) {
        this.no_of_positions = no_of_positions;
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
    public String getRequisition_approval_notes() {
        return requisition_approval_notes;
    }
    public void setRequisition_approval_notes(String requisition_approval_notes) {
        this.requisition_approval_notes = requisition_approval_notes;
    }

}
