package com.bob.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "job_application_fee")
@Getter
@Setter
public class JobApplicationFeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_application_fee_id", nullable = false)
    private Integer jobApplicationFeeId;

    @Column(name = "position_id", nullable = false)
    private UUID positionId;

    @Column(name = "requisition_id", nullable = false)
    private UUID requisitionId;

    @Column(name = "application_fee_id", nullable = false)
    private Integer applicationFeeId;

}
