package com.example.CandidateDetails.Mapper;

import com.example.CandidateDetails.Model.Candidates;
import com.example.CandidateDetails.Model.Interviews;
import com.example.CandidateDetails.Model.JobRequisitions;
import com.example.CandidateDetails.Model.Position;
import com.example.CandidateDetails.entity.PositionsEntity;
import com.example.CandidateDetails.util.Util;
import com.example.CandidateDetails.dto.InterviewResponse;

public class InterviewMapper {

    public static InterviewResponse interviewToInterviewResponse(Interviews interview, Candidates candidate, PositionsEntity position, JobRequisitions jobRequisition) {
        InterviewResponse response = new InterviewResponse();
        response.setInterviewTime(interview.getSchedule_at() != null ? interview.getSchedule_at().toString() : null);
        response.setInterviewTitle(Util.nullcheckString(position.getPositionTitle()));
        response.setCandidateName(Util.nullcheckString(candidate.getFull_name()));
        response.setCandidateSkill(Util.nullcheckString(candidate.getCurrent_designation()));
        response.setApplicationStatus(Util.nullcheckString(interview.getStatus()));
        response.setInterviewName(Util.nullcheckString(interview.getInterviewer()));
        response.setRequisition_code(Util.nullcheckString(jobRequisition.getRequisition_code()));
        return response;
    }
}