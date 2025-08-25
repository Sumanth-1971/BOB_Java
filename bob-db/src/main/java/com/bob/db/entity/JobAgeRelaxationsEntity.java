package com.bob.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "job_age_relaxations")
@Getter
@Setter
public class JobAgeRelaxationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_relaxation_id", nullable = false)
    private Integer jobRelaxationId;

    @Column(name = "position_id")
    private UUID positionId;

    @Column(name = "age_relaxation_id", nullable = false)
    private Integer ageRelaxationId;

}
