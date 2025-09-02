package com.example.CandidateDetails.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class InterviewerResponse {
    private Integer interview_id;

    private UUID candidate_id;

    private UUID position_id;

    private String type;

    private LocalDateTime scheduled_at;
    @Column(precision = 4, scale = 2)
    private BigDecimal time;

    private String status;

    private String interviewer;


    @JsonProperty("interviewer_id")
    private Integer user_id;
    private String interviewer_email;

}
