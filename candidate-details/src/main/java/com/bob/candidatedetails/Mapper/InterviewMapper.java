package com.bob.candidatedetails.Mapper;

import com.bob.db.entity.Candidates;
import com.bob.db.entity.Interviews;
import com.bob.db.entity.Position;
import com.bob.db.dto.InterviewResponse;
import com.bob.candidatedetails.util.Util;

public class InterviewMapper {

    public static InterviewResponse interviewToInterviewResponse(Interviews interview, Candidates candidate, Position position) {
        InterviewResponse response = new InterviewResponse();
        response.setInterviewTime(interview.getSchedule_at() != null ? interview.getSchedule_at().toString() : null);
        response.setInterviewTitle(Util.nullcheckString(position.getPosition_title()));
        response.setCandidateName(Util.nullcheckString(candidate.getFull_name()));
        response.setCandidateSkill(Util.nullcheckString(candidate.getCurrent_designation()));

        return response;
    }
}