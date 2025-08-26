package com.example.JobCreation.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.util.UUID;

public class JobPositionsDTO {

    private UUID position_id;
    private UUID requisition_id;
    private String position_title;
    private String Requisition_code;
    private String Requisition_title;
    private String position_code;
    private String description;
    private String roles_responsibilities;
    private Integer grade_id;
    private String employment_type;
    private Integer eligibility_age_min;
    private Integer eligibility_age_max;
    private String mandatory_qualification;
    private String preferred_qualification;

    @Column(precision = 4, scale = 1)
    private BigDecimal mandatory_experience;

    @Column(precision = 4, scale = 1)
    private BigDecimal preferred_experience;

    private BigDecimal probation_period;

    private String documents_required;

    @Column(precision = 5, scale =2)
    private BigDecimal min_credit_score;

    /*
        //age_relaxation - table
        private Integer age_relaxation_id;

        //Application_Fee - table
        private Integer application_fee_id;
    */
    //posting-location - table
    private Integer dept_id;
    private Long location_id;

    //job Vacancies
    private Integer special_cat_id;
    private Integer reservation_cat_id;
    private Integer no_of_vacancies;

    //Job Selection Process table
    private String selection_procedure;

    private BigDecimal min_salary;
    private BigDecimal max_salary;



    private String position_status;

    public JobPositionsDTO() {
    }

    public JobPositionsDTO(UUID position_id, UUID requisition_id, String position_title, String requisition_code, String requisition_title, String position_code, String description, String roles_responsibilities, Integer grade_id, String employment_type, Integer eligibility_age_min, Integer eligibility_age_max, String mandatory_qualification, String preferred_qualification, BigDecimal mandatory_experience, BigDecimal preferred_experience, BigDecimal probation_period, String documents_required, BigDecimal min_credit_score, Integer dept_id, Long location_id, Integer special_cat_id, Integer reservation_cat_id, Integer no_of_vacancies, String selection_procedure, BigDecimal min_salary, BigDecimal max_salary, String position_status) {
        this.position_id = position_id;
        this.requisition_id = requisition_id;
        this.position_title = position_title;
        Requisition_code = requisition_code;
        Requisition_title = requisition_title;
        this.position_code = position_code;
        this.description = description;
        this.roles_responsibilities = roles_responsibilities;
        this.grade_id = grade_id;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
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
        this.dept_id = dept_id;
        this.location_id = location_id;
        this.special_cat_id = special_cat_id;
        this.reservation_cat_id = reservation_cat_id;
        this.no_of_vacancies = no_of_vacancies;
        this.selection_procedure = selection_procedure;

        this.position_status = position_status;
    }

    public BigDecimal getMax_salary() {
        return max_salary;
    }
    public void setMax_salary(BigDecimal max_salary) {
        this.max_salary = max_salary;
    }
    public BigDecimal getMin_salary() {
        return min_salary;
    }
    public void setMin_salary(BigDecimal min_salary) {
        this.min_salary = min_salary;
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

    public String getPosition_code() {
        return position_code;
    }

    public void setPosition_code(String position_code) {
        this.position_code = position_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoles_responsibilities() {
        return roles_responsibilities;
    }

    public void setRoles_responsibilities(String roles_responsibilities) {
        this.roles_responsibilities = roles_responsibilities;
    }

    public Integer getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(Integer grade_id) {
        this.grade_id = grade_id;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public void setEmployment_type(String employment_type) {
        this.employment_type = employment_type;
    }

    public Integer getEligibility_age_min() {
        return eligibility_age_min;
    }

    public void setEligibility_age_min(Integer eligibility_age_min) {
        this.eligibility_age_min = eligibility_age_min;
    }

    public Integer getEligibility_age_max() {
        return eligibility_age_max;
    }

    public void setEligibility_age_max(Integer eligibility_age_max) {
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

    public BigDecimal getProbation_period() {
        return probation_period;
    }
    public void setProbation_period(BigDecimal probation_period) {
        this.probation_period = probation_period;
    }
    public String getDocuments_required() {
        return documents_required;
    }

    public void setDocuments_required(String documents_required) {
        this.documents_required = documents_required;
    }

    public BigDecimal getMin_credit_score() {
        return min_credit_score;
    }

    public void setMin_credit_score(BigDecimal min_credit_score) {
        this.min_credit_score = min_credit_score;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    public Integer getSpecial_cat_id() {
        return special_cat_id;
    }

    public void setSpecial_cat_id(Integer special_cat_id) {
        this.special_cat_id = special_cat_id;
    }

    public Integer getReservation_cat_id() {
        return reservation_cat_id;
    }

    public void setReservation_cat_id(Integer reservation_cat_id) {
        this.reservation_cat_id = reservation_cat_id;
    }

    public Integer getNo_of_vacancies() {
        return no_of_vacancies;
    }

    public void setNo_of_vacancies(Integer no_of_vacancies) {
        this.no_of_vacancies = no_of_vacancies;
    }

    public String getSelection_procedure() {
        return selection_procedure;
    }

    public void setSelection_procedure(String selection_procedure) {
        this.selection_procedure = selection_procedure;
    }

    public String getPosition_status() {
        return position_status;
    }

    public void setPosition_status(String position_status) {
        this.position_status = position_status;
    }

    public String getRequisition_code() {
        return Requisition_code;
    }
    public void setRequisition_code(String requisition_code) {
        Requisition_code = requisition_code;
    }
    public String getRequisition_title() {
        return Requisition_title;
    }
    public void setRequisition_title(String requisition_title) {
        Requisition_title = requisition_title;
    }
}