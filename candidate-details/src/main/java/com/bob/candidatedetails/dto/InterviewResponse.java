package com.bob.candidatedetails.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewResponse {
    private String interviewTime;
    private String interviewTitle;
    private String candidateName;
    private String candidateSkill;
}