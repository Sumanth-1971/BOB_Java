package com.example.CandidateDetails.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class FeedbackResponse {

    @JsonProperty("id")
    private UUID approvalId;

    private String interviewerName;
    private String comments;
    private LocalDateTime actionDate;
    private String status;
}
