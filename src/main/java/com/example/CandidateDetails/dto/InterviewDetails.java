package com.example.CandidateDetails.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class InterviewDetails {

    private UUID candidate_id;
    private UUID position_id;
    private LocalDate date;

    private LocalTime time;
    @JsonProperty("interviewer_id")
    private Integer user_id;
    private String interviewer_name;
    private String interviewer_email;

    private String status;

    public InterviewDetails() {
    }

    public InterviewDetails(UUID candidate_id, UUID position_id, LocalDate date, LocalTime time, Integer user_id, String interviewer_name, String interviewer_email, String status) {
        this.candidate_id = candidate_id;
        this.position_id = position_id;
        this.date = date;
        this.time = time;
        this.user_id = user_id;
        this.interviewer_name = interviewer_name;
        this.interviewer_email = interviewer_email;
        this.status = status;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getInterviewer_name() {
        return interviewer_name;
    }

    public void setInterviewer_name(String interviewer_name) {
        this.interviewer_name = interviewer_name;
    }

    public String getInterviewer_email() {
        return interviewer_email;
    }

    public void setInterviewer_email(String interviewer_email) {
        this.interviewer_email = interviewer_email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
