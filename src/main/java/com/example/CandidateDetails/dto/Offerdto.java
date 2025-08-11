package com.example.CandidateDetails.dto;
import java.util.UUID;
public class Offerdto {

    private UUID candidate_id;
    private UUID position_id;
    private int salary;

    private String offer_letter_path;

    public Offerdto() {
    }

    public Offerdto(UUID candidate_id, UUID position_id, int salary, String offer_letter_path) {
        this.candidate_id = candidate_id;
        this.position_id = position_id;
        this.salary = salary;
        this.offer_letter_path = offer_letter_path;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getOffer_letter_path() {
        return offer_letter_path;
    }

    public void setOffer_letter_path(String offer_letter_path) {
        this.offer_letter_path = offer_letter_path;
    }
}
