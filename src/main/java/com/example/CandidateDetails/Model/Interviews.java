package com.example.CandidateDetails.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "interviews")
public class Interviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer interview_id;

    private UUID candidate_id;

    private UUID position_id;

    private String type;

    private LocalDateTime scheduled_at;
    @Column(precision = 4, scale = 2)
    private BigDecimal time;

    private String status;

    private String interviewer;

    public Interviews() {
    }

    public Interviews(int interview_id, UUID candidate_id, UUID position_id, String type, LocalDateTime schedule_at, BigDecimal time, String status, String interviewer) {
        this.interview_id = interview_id;
        this.candidate_id = candidate_id;
        this.position_id = position_id;
        this.type = type;
        this.scheduled_at = schedule_at;
        this.time = time;
        this.status = status;
        this.interviewer = interviewer;
    }


    public int getInterview_id() {
        return interview_id;
    }

    public void setInterview_id(int interview_id) {
        this.interview_id = interview_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getSchedule_at() {
        return scheduled_at;
    }

    public void setSchedule_at(LocalDateTime schedule_at) {
        this.scheduled_at = schedule_at;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public UUID getCandidateId() {
        return this.candidate_id;
    }

    public UUID getPositionId() {
        return this.position_id;
    }

    public LocalDateTime getScheduled_at() {
        return scheduled_at;
    }

    public void setScheduled_at(LocalDateTime scheduled_at) {
        this.scheduled_at = scheduled_at;
    }
}
