package com.example.CandidateDetails.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class Interviewdto {

    @NotNull(message = "Candidate ID cannot be null")
    private UUID candidate_id;

    private LocalDate date;

    private LocalTime interview_time;

    private Integer userId;

    private UUID position_id;
    private Integer interviewer_id;
    private String interviewer_name;
    private String interviewer_email;

    public Interviewdto() {
    }

    public Interviewdto(UUID candidate_id, LocalDate date, LocalTime interview_time,  UUID position_id, Integer interviewer_id, String interviewer_name, String interviewer_email) {
        this.candidate_id = candidate_id;
        this.date = date;
        this.interview_time = interview_time;
        this.userId = userId;
        this.position_id = position_id;
        this.interviewer_id = interviewer_id;
        this.interviewer_name = interviewer_name;
        this.interviewer_email = interviewer_email;
    }

    public Integer getInterviewer_id() {
        return interviewer_id;
    }
    public void setInterviewer_id(Integer interviewer_id) {
        this.interviewer_id = interviewer_id;
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


    public UUID getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(UUID candidate_id) {
        this.candidate_id = candidate_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getInterview_time() {
        return interview_time;
    }

    public void setInterview_time(LocalTime interview_time) {
        this.interview_time = interview_time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }
}
