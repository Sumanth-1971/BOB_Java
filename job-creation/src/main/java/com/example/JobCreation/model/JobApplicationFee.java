package com.example.JobCreation.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "job_application_fee")
public class JobApplicationFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer job_application_fee_id;
    private UUID position_id;
    private Integer application_fee_id;

    public JobApplicationFee() {
    }

    public JobApplicationFee(Integer job_application_fee_id, UUID position_id, Integer application_fee_id) {
        this.job_application_fee_id = job_application_fee_id;
        this.position_id = position_id;
        this.application_fee_id = application_fee_id;
    }

    public Integer getJob_application_fee_id() {
        return job_application_fee_id;
    }

    public void setJob_application_fee_id(Integer job_application_fee_id) {
        this.job_application_fee_id = job_application_fee_id;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public Integer getApplication_fee_id() {
        return application_fee_id;
    }

    public void setApplication_fee_id(Integer application_fee_id) {
        this.application_fee_id = application_fee_id;
    }
}
