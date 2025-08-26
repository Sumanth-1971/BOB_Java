package com.bob.candidatedetails.Mapper;

import com.bob.db.entity.*;
import com.bob.db.dto.InterviewResponse;
import com.bob.candidatedetails.util.Util;

public class InterviewMapper {

    public static InterviewResponse interviewToInterviewResponse(Interviews interview, Candidates candidate, PositionsEntity position, JobRequisitionsEntity requisition) {
        InterviewResponse response = new InterviewResponse();
        response.setInterviewTime(interview.getSchedule_at() != null ? interview.getSchedule_at().toString() : null);
        response.setInterviewTitle(Util.nullcheckString(position.getPositionTitle()));
        response.setCandidateName(Util.nullcheckString(candidate.getFull_name()));
        response.setCandidateSkill(Util.nullcheckString(candidate.getCurrent_designation()));
        response.setApplicationStatus(Util.nullcheckString(interview.getStatus()));
        response.setRequisition_code(Util.nullcheckString(requisition.getRequisitionCode()));
        response.setInterviewName(Util.nullcheckString(interview.getInterviewer()));
        return response;
    }
}