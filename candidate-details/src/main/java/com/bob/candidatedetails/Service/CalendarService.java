package com.bob.candidatedetails.Service;

import com.bob.candidatedetails.Mapper.InterviewMapper;
import com.bob.candidatedetails.Model.Candidates;
import com.bob.candidatedetails.Model.Interviews;
import com.bob.candidatedetails.Model.Position;
import com.bob.candidatedetails.Repository.CandidateRepository;
import com.bob.candidatedetails.Repository.InterviewsRepository;
import com.bob.candidatedetails.Repository.PositionRepository;
import com.bob.candidatedetails.dto.InterviewResponse;
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
    private PositionRepository positionRepository;

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
        List<Position> positionsList = positionRepository.findAllById(positionIds);

        interviewsList.forEach(interview -> {
            Candidates candidate = candidatesList.stream()
                    .filter(c -> c.getCandidate_id().equals(interview.getCandidateId()))
                    .findFirst()
                    .orElse(null);

            Position position = positionsList.stream()
                    .filter(p -> p.getPosition_id().equals(interview.getPositionId()))
                    .findFirst()
                    .orElse(null);

            if (candidate != null && position != null) {
                InterviewResponse interviewResponse = InterviewMapper.interviewToInterviewResponse(interview, candidate, position);
                interviewResponseList.add(interviewResponse);
            }
        });

        return interviewResponseList;

    }
}