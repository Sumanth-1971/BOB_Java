package com.bob.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "job_requisitions")
@Getter
@Setter
public class JobRequisitionsEntity {
    @Id
    @Column(name = "requisition_id", updatable = false, nullable = false)
    private UUID requisitionId;
    @Column(name = "requisition_code", unique = true, nullable = false)
    private String requisitionCode;
    @Column(name = "requisition_title", nullable = false)
    private String requisitionTitle;

    @Column(name = "requisition_description", columnDefinition = "text")
    private String requisitionDescription;
    @Column(name = "registration_start_date")
    private LocalDate registrationStartDate;
    @Column(name = "registration_end_date")
    private LocalDate registrationEndDate;
    @Column(name = "requisition_status")
    private String requisitionStatus;

    @Column(name = "requisition_comments", columnDefinition = "text")
    private String requisitionComments;

    @Column(name = "requisition_approval")
    private String requisitionApproval;

    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "others" , columnDefinition = "jsonb")
    private String others;
    @Column(name = "no_of_positions")
    private Integer noOfPositions;

    @Column(name = "job_postings", columnDefinition = "text")
    private String jobPostings;

    @Column(name = "requisition_approval_notes", columnDefinition = "text")
    private String requisitionApprovalNotes;
    @Column(name = "isactive")
    private Integer isactive = 1;

}
