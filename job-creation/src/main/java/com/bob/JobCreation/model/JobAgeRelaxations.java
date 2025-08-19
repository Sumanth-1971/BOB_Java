package com.bob.JobCreation.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "job_age_relaxations")
public class JobAgeRelaxations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer job_relaxation_id;
    private UUID position_id;

    private Integer age_relaxation_id;

    public JobAgeRelaxations() {
    }

    public JobAgeRelaxations(Integer job_relaxation_id, UUID position_id, Integer age_relaxation_id) {
        this.job_relaxation_id = job_relaxation_id;
        this.position_id = position_id;
        this.age_relaxation_id = age_relaxation_id;
    }

    public Integer getJob_relaxation_id() {
        return job_relaxation_id;
    }

    public void setJob_relaxation_id(Integer job_relaxation_id) {
        this.job_relaxation_id = job_relaxation_id;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public Integer getAge_relaxation_id() {
        return age_relaxation_id;
    }

    public void setAge_relaxation_id(Integer age_relaxation_id) {
        this.age_relaxation_id = age_relaxation_id;
    }
}
