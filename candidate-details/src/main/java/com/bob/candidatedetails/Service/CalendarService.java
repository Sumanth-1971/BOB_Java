package com.bob.candidatedetails.Service;

import com.bob.candidatedetails.Mapper.InterviewMapper;
import com.bob.db.entity.*;
import com.bob.db.repository.CandidateRepository;
import com.bob.db.repository.InterviewsRepository;
import com.bob.db.repository.JobRequisitionsRepository;
import com.bob.db.repository.PositionsRepository;
import com.bob.db.dto.InterviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CalendarService {

    @Autowired
    private InterviewsRepository interviewsRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRequisitionsRepository jobRequisitionsRepository;

    @Autowired
    private PositionsRepository positionsRepository;

    public List<InterviewResponse> getInterviewSchedulesBetween(long startTimestamp, long endTimestamp) {
        LocalDateTime startDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTimestamp), ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(endTimestamp), ZoneId.systemDefault());

        List<Interviews> interviewsList = interviewsRepository.findByScheduledAtBetween(startDate, endDate);
        if (interviewsList.isEmpty()) {
            return new ArrayList<>();
        }

        List<InterviewResponse> interviewResponseList = new ArrayList<>();

        //fetch all candidate IDs from the interviews
        List<UUID> candidateIds = interviewsList.stream()
                .map(Interviews::getCandidateId)
                .toList();

        List<UUID> positionIds = interviewsList.stream()
                .map(Interviews::getPositionId)
                .toList();

        //fetch all candidates from the candidate repository
        List<Candidates> candidatesList = candidateRepository.findAllById(candidateIds);

        //fetch all positions from the position repository
        List<PositionsEntity> positionsList = positionsRepository.findAllById(positionIds);

        List<UUID> requisitionIds = positionsList.stream()
                .map(PositionsEntity::getRequisitionId)
                .toList();
        List<JobRequisitionsEntity> jobRequisitionsList = jobRequisitionsRepository.findAllById(requisitionIds);

        interviewsList.forEach(interview -> {
            Candidates candidate = candidatesList.stream()
                    .filter(c -> c.getCandidate_id().equals(interview.getCandidateId()))
                    .findFirst()
                    .orElse(null);

            PositionsEntity position = positionsList.stream()
                    .filter(p -> p.getPositionId().equals(interview.getPositionId()))
                    .findFirst()
                    .orElse(null);
            JobRequisitionsEntity requisition = jobRequisitionsList.stream()
                    .filter(r -> r.getRequisitionId().equals(position.getRequisitionId()))
                    .findFirst()
                    .orElse(null);

            if (candidate != null && position != null && requisition != null) {
                InterviewResponse interviewResponse = InterviewMapper.interviewToInterviewResponse(interview, candidate, position,requisition);
                interviewResponseList.add(interviewResponse);
            }
        });

        return interviewResponseList;

    }
}