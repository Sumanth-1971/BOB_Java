package com.bob.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "positions")
@Getter
@Setter
public class PositionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "position_id", nullable = false, columnDefinition = "uuid")
    private UUID positionId;

    @Column(name = "requisition_id")
    private UUID requisitionId;

    @Column(name = "position_title", nullable = false)
    private String positionTitle;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "position_code")
    private String positionCode;

    @Column(name = "roles_responsibilities", columnDefinition = "text")
    private String rolesResponsibilities;

    @Column(name = "grade_id")
    private Integer gradeId;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "eligibility_age_min")
    private Integer eligibilityAgeMin;

    @Column(name = "eligibility_age_max")
    private Integer eligibilityAgeMax;

    @Column(name = "mandatory_qualification", columnDefinition = "text")
    private String mandatoryQualification;

    @Column(name = "preferred_qualification", columnDefinition = "text")
    private String preferredQualification;

    @Column(name = "mandatory_experience")
    private BigDecimal mandatoryExperience;

    @Column(name = "preferred_experience")
    private BigDecimal preferredExperience;

    @Column(name = "probation_period")
    private BigDecimal probationPeriod;

    @Column(name = "documents_required", columnDefinition = "text")
    private String documentsRequired;

    @Column(name = "min_credit_score")
    private BigDecimal minCreditScore;

    @Column(name = "position_status")
    private String positionStatus;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
