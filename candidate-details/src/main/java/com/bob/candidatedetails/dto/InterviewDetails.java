package com.bob.candidatedetails.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class InterviewDetails {

    private UUID candidate_id;

    private LocalDate date;

    private LocalTime time;

    private UUID position_id;

    private String status;

//    private Integer interview_id;

    public InterviewDetails() {
    }

    public InterviewDetails(UUID candidate_id, LocalDate date, LocalTime time, UUID position_id, String status) {
        this.candidate_id = candidate_id;
        this.date = date;
        this.time = time;
        this.position_id = position_id;
        this.status = status;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
