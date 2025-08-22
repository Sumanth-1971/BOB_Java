package com.example.CandidateDetails.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class InterviewResponse {
    private String interviewTime;
    private String interviewTitle;
    private String candidateName;
    private String candidateSkill;
    private String applicationStatus;
}
