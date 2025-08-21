package com.bob.db.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "candidate_nationality")
public class CandidateNationality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidate_nationality_id;

    private UUID position_id;

    private int country_id;

    public CandidateNationality() {
    }

    public CandidateNationality(UUID position_id, int country_id) {
        this.position_id = position_id;
        this.country_id = country_id;
    }

    public int getCandidate_nationality_id() {
        return candidate_nationality_id;
    }

    public void setCandidate_nationality_id(int candidate_nationality_id) {
        this.candidate_nationality_id = candidate_nationality_id;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
