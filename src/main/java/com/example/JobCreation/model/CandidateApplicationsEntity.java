package com.example.JobCreation.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidate_applications")
@Data
public class CandidateApplicationsEntity {
    @Id
    @Column(name = "application_id")
    private UUID applicationId;

//    @OneToOne
//    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id", insertable = false, updatable = false)
//    private Candidate candidate;
//
//    @OneToOne
//    @JoinColumn(name = "position_id", referencedColumnName = "position_id", insertable = false, updatable = false)
//    private Positions position;

    @Column(name = "position_id", nullable = false)
    private UUID positionId;

    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "application_date")
    private LocalDateTime applicationDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
