package com.example.MasterData.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "positions")
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

    public PositionsEntity() {
    }

    public UUID getPositionId() {
        return positionId;
    }

    public void setPositionId(UUID positionId) {
        this.positionId = positionId;
    }

    public UUID getRequisitionId() {
        return requisitionId;
    }

    public void setRequisitionId(UUID requisitionId) {
        this.requisitionId = requisitionId;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getRolesResponsibilities() {
        return rolesResponsibilities;
    }

    public void setRolesResponsibilities(String rolesResponsibilities) {
        this.rolesResponsibilities = rolesResponsibilities;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public Integer getEligibilityAgeMin() {
        return eligibilityAgeMin;
    }

    public void setEligibilityAgeMin(Integer eligibilityAgeMin) {
        this.eligibilityAgeMin = eligibilityAgeMin;
    }

    public Integer getEligibilityAgeMax() {
        return eligibilityAgeMax;
    }

    public void setEligibilityAgeMax(Integer eligibilityAgeMax) {
        this.eligibilityAgeMax = eligibilityAgeMax;
    }

    public String getMandatoryQualification() {
        return mandatoryQualification;
    }

    public void setMandatoryQualification(String mandatoryQualification) {
        this.mandatoryQualification = mandatoryQualification;
    }

    public String getPreferredQualification() {
        return preferredQualification;
    }

    public void setPreferredQualification(String preferredQualification) {
        this.preferredQualification = preferredQualification;
    }

    public BigDecimal getMandatoryExperience() {
        return mandatoryExperience;
    }

    public void setMandatoryExperience(BigDecimal mandatoryExperience) {
        this.mandatoryExperience = mandatoryExperience;
    }

    public BigDecimal getPreferredExperience() {
        return preferredExperience;
    }

    public void setPreferredExperience(BigDecimal preferredExperience) {
        this.preferredExperience = preferredExperience;
    }

    public BigDecimal getProbationPeriod() {
        return probationPeriod;
    }

    public void setProbationPeriod(BigDecimal probationPeriod) {
        this.probationPeriod = probationPeriod;
    }

    public String getDocumentsRequired() {
        return documentsRequired;
    }

    public void setDocumentsRequired(String documentsRequired) {
        this.documentsRequired = documentsRequired;
    }

    public BigDecimal getMinCreditScore() {
        return minCreditScore;
    }

    public void setMinCreditScore(BigDecimal minCreditScore) {
        this.minCreditScore = minCreditScore;
    }

    public String getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(String positionStatus) {
        this.positionStatus = positionStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
