package com.example.CandidateDetails.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class FeedbackDto {

    @JsonProperty("candidate_id")
    private UUID candidateId;
    @JsonProperty("position_id")
    private UUID positionId;

    @JsonProperty("interviewer_id")
    private Integer interviewerId;
    @JsonProperty("interviewer_name")
    private String interviewerName;
    @JsonProperty("interviewer_email")
    private String interviewerEmail;

    private String comments;
    private String status;

}
