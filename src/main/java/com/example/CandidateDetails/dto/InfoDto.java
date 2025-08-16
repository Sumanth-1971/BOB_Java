package com.example.CandidateDetails.dto;

import java.util.UUID;

public class InfoDto {

    private UUID candidate_id;

    private UUID position_id;


    public InfoDto() {
    }

    public InfoDto(UUID candidate_id, UUID position_id) {
        this.candidate_id = candidate_id;
        this.position_id = position_id;
    }

    public UUID getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(UUID candidate_id) {
        this.candidate_id = candidate_id;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }
}
