package com.example.CandidateDetails.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long state_id;

    private String state_name;

    private Long country_id;

    private LocalDateTime created_date;


    private String created_by;

    public State() {
    }


    public State(Long state_id, String state_name, Long country_id, LocalDateTime created_date, String created_by) {
        this.state_id = state_id;
        this.state_name = state_name;
        this.country_id = country_id;
        this.created_date = created_date;
        this.created_by = created_by;
    }

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}

