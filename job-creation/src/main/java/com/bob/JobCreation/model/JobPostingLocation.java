package com.bob.JobCreation.model;

import jakarta.persistence.*;


import java.util.UUID;

@Entity
@Table(name = "job_posting_location")
public class JobPostingLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer posting_location_id ;
    private UUID position_id;

    private Integer dept_id;
    private Long location_id;


    public JobPostingLocation() {
    }

    public JobPostingLocation(Integer posting_location_id, UUID position_id, Integer dept_id, Long location_id) {
        this.posting_location_id = posting_location_id;
        this.position_id = position_id;
        this.dept_id = dept_id;
        this.location_id = location_id;
    }
    public Integer getPosting_location_id() {
        return posting_location_id;
    }

    public void setPosting_location_id(Integer posting_location_id) {
        this.posting_location_id = posting_location_id;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }
}
