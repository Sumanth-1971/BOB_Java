package com.example.CandidateDetails.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "job_posting_location")
public class JobPostingLocation {
    @Id
    private Integer posting_location_id;

    private UUID posting_id;

    private Integer dept_id;

    private Integer location_id;

    public JobPostingLocation() {
    }

    public JobPostingLocation(Integer posting_location_id, UUID posting_id, Integer dept_id, Integer location_id) {
        this.posting_location_id = posting_location_id;
        this.posting_id = posting_id;
        this.dept_id = dept_id;
        this.location_id = location_id;
    }

    public Integer getPosting_location_id() {
        return posting_location_id;
    }

    public void setPosting_location_id(Integer posting_location_id) {
        this.posting_location_id = posting_location_id;
    }

    public UUID getPosting_id() {
        return posting_id;
    }

    public void setPosting_id(UUID posting_id) {
        this.posting_id = posting_id;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }
}
