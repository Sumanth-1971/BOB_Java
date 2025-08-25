package com.bob.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidate_applications")
@Getter
@Setter
public class CandidateApplicationsEntity {
    @Id
    @Column(name = "application_id")
    private UUID applicationId;

    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(name = "position_id", nullable = false)
    private UUID positionId;

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "application_date")
    private LocalDateTime applicationDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
