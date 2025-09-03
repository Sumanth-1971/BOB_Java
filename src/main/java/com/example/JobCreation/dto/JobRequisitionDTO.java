package com.example.JobCreation.dto;



import java.time.LocalDate;
import java.util.UUID;

public class JobRequisitionDTO {
    private UUID requisition_id;
    private String requisition_code;
    private String requisition_title;

    private String requisition_description;
    private LocalDate registration_start_date;
    private LocalDate registration_end_date;
    private String requisition_status;
    
    private String requisition_comments;
    private String requisition_approval;
    private Integer no_of_positions;
    private String job_postings;
    private String requisition_approval_notes;

    private Integer fullfillment;
    private Integer count;
    public JobRequisitionDTO() {
    }

    public JobRequisitionDTO(UUID requisition_id, String requisition_code, String requisition_title, String requisition_description, LocalDate registration_start_date, LocalDate registration_end_date, String requisition_status, String requisition_comments, String requisition_approval, Integer no_of_positions, String job_postings, String requisition_approval_notes) {
        this.requisition_id = requisition_id;
        this.requisition_code = requisition_code;
        this.requisition_title = requisition_title;
        this.requisition_description = requisition_description;
        this.registration_start_date = registration_start_date;
        this.registration_end_date = registration_end_date;
        this.requisition_status = requisition_status;
        this.requisition_comments = requisition_comments;
        this.requisition_approval = requisition_approval;
        this.no_of_positions = no_of_positions;
        this.job_postings = job_postings;
        this.requisition_approval_notes = requisition_approval_notes;
    }
    public Integer getFullfillment() {
        return fullfillment;
    }
    public void setFullfillment(Integer fullfillment) {
        this.fullfillment = fullfillment;
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

    public Integer getNo_of_positions() {
        return no_of_positions;
    }

    public void setNo_of_positions(Integer no_of_positions) {
        this.no_of_positions = no_of_positions;
    }

    public String getJob_postings() {
        return job_postings;
    }

    public void setJob_postings(String job_postings) {
        this.job_postings = job_postings;
    }

    public String getRequisition_approval_notes() {
        return requisition_approval_notes;
    }

    public void setRequisition_approval_notes(String requisition_approval_notes) {
        this.requisition_approval_notes = requisition_approval_notes;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
