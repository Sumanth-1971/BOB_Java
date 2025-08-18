package com.example.JobCreation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "approval")
@Getter
@Setter
public class WorkflowApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "approval_id")
    private UUID approvalId;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "entity_id")
    private UUID entityId;

    @Column(name = "step_number")
    private Integer stepNumber;

    @Column(name = "approver_role")
    private String approverRole;

    @Column(name = "approver_id")
    private Integer approverId;

    @Column(name = "action")
    private String action;

    @Column(name = "action_date")
    private LocalDateTime actionDate;

    @Column(name = "comments")
    private String comments;

    @Column(name = "status")
    private String status;
}