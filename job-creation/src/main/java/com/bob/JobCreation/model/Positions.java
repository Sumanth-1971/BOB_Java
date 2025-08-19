package com.bob.JobCreation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "positions")
public class Positions {
    @Id
    private UUID position_id;

    private UUID requisition_id;

    private String position_title;

    private String description;

    private String position_code;

    private String roles_responsibilities;

    private int grade_id;

    private String employment_type;

    private int eligibility_age_min;

    private int eligibility_age_max;

    private String mandatory_qualification;

    private String preferred_qualification;
    @Column(precision = 4, scale = 1)
    private BigDecimal mandatory_experience;

    @Column(precision = 4, scale = 1)
    private BigDecimal preferred_experience;

    private int probation_period;


    private String documents_required;

    @Column(precision = 5, scale =2)
    private BigDecimal min_credit_score;


    private String position_status;

    private String created_by;

    private LocalDateTime created_date;


    private String updated_by;

    private LocalDateTime updated_date;


    public Positions() {
    }

    public Positions(UUID position_id, UUID requisition_id, String position_title, String description, String position_code, String roles_responsibilities, int grade_id, String employment_type, int eligibility_age_min, int eligibility_age_max, String mandatory_qualification, String preferred_qualification, BigDecimal mandatory_experience, BigDecimal preferred_experience, int probation_period, String documents_required, BigDecimal min_credit_score, String position_status, String created_by, LocalDateTime created_date, String updated_by, LocalDateTime updated_date) {
        this.position_id = position_id;
        this.requisition_id = requisition_id;
        this.position_title = position_title;
        this.description = description;
        this.position_code = position_code;
        this.roles_responsibilities = roles_responsibilities;
        this.grade_id = grade_id;
        this.employment_type = employment_type;
        this.eligibility_age_min = eligibility_age_min;
        this.eligibility_age_max = eligibility_age_max;
        this.mandatory_qualification = mandatory_qualification;
        this.preferred_qualification = preferred_qualification;
        this.mandatory_experience = mandatory_experience;
        this.preferred_experience = preferred_experience;
        this.probation_period = probation_period;
        this.documents_required = documents_required;
        this.min_credit_score = min_credit_score;
        this.position_status = position_status;
        this.created_by = created_by;
        this.created_date = created_date;
        this.updated_by = updated_by;
        this.updated_date = updated_date;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public UUID getRequisition_id() {
        return requisition_id;
    }

    public void setRequisition_id(UUID requisition_id) {
        this.requisition_id = requisition_id;
    }

    public String getPosition_title() {
        return position_title;
    }

    public void setPosition_title(String position_title) {
        this.position_title = position_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition_code() {
        return position_code;
    }

    public void setPosition_code(String position_code) {
        this.position_code = position_code;
    }

    public String getRoles_responsibilities() {
        return roles_responsibilities;
    }

    public void setRoles_responsibilities(String roles_responsibilities) {
        this.roles_responsibilities = roles_responsibilities;
    }

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public void setEmployment_type(String employment_type) {
        this.employment_type = employment_type;
    }

    public int getEligibility_age_min() {
        return eligibility_age_min;
    }

    public void setEligibility_age_min(int eligibility_age_min) {
        this.eligibility_age_min = eligibility_age_min;
    }

    public int getEligibility_age_max() {
        return eligibility_age_max;
    }

    public void setEligibility_age_max(int eligibility_age_max) {
        this.eligibility_age_max = eligibility_age_max;
    }

    public String getMandatory_qualification() {
        return mandatory_qualification;
    }

    public void setMandatory_qualification(String mandatory_qualification) {
        this.mandatory_qualification = mandatory_qualification;
    }

    public String getPreferred_qualification() {
        return preferred_qualification;
    }

    public void setPreferred_qualification(String preferred_qualification) {
        this.preferred_qualification = preferred_qualification;
    }

    public BigDecimal getMandatory_experience() {
        return mandatory_experience;
    }

    public void setMandatory_experience(BigDecimal mandatory_experience) {
        this.mandatory_experience = mandatory_experience;
    }

    public BigDecimal getPreferred_experience() {
        return preferred_experience;
    }

    public void setPreferred_experience(BigDecimal preferred_experience) {
        this.preferred_experience = preferred_experience;
    }

    public int getProbation_period() {
        return probation_period;
    }

    public void setProbation_period(int probation_period) {
        this.probation_period = probation_period;
    }

    public String getDocuments_required() {
        return documents_required;
    }

    public void setDocument_required(String documents_required) {
        this.documents_required = documents_required;
    }

    public BigDecimal getMin_credit_score() {
        return min_credit_score;
    }

    public void setMin_credit_score(BigDecimal min_credit_score) {
        this.min_credit_score = min_credit_score;
    }

    public String getPosition_status() {
        return position_status;
    }

    public void setPosition_status(String position_status) {
        this.position_status = position_status;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDateTime updated_date) {
        this.updated_date = updated_date;
    }
}