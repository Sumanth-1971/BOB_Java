package com.example.JobCreation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "positions")
@Getter
@Setter
public class Positions {
        @Id
        @Column(name = "position_id", columnDefinition = "uuid")
        private UUID position_id;

        @Column(name = "requisition_id", columnDefinition = "uuid")
        private UUID requisition_id;

        @Column(name = "position_title" )
        private String position_title;

        @Column(name = "description", columnDefinition = "text")
        private String description;

        @Column(name = "position_code")
        private String position_code;

        @Column(name = "roles_responsibilities", columnDefinition = "text")
        private String roles_responsibilities;

        @Column(name = "grade_id")
        private Integer grade_id;

        @Column(name = "employment_type")
        private String employment_type;

        @Column(name = "eligibility_age_min")
        private Integer eligibility_age_min;

        @Column(name = "eligibility_age_max")
        private Integer eligibility_age_max;

        @Column(name = "mandatory_qualification", columnDefinition = "text")
        private String mandatory_qualification;

        @Column(name = "preferred_qualification", columnDefinition = "text")
        private String preferred_qualification;

        @Column(name = "mandatory_experience")
        private BigDecimal mandatory_experience;

        @Column(name = "preferred_experience")
        private BigDecimal preferred_experience;

        @Column(name = "probation_period")
        private BigDecimal probation_period;

        @Column(name = "documents_required", columnDefinition = "text")
        private String documents_required;

        @Column(name = "min_credit_score")
        private BigDecimal min_credit_score;

        @Column(name = "position_status")
        private String position_status;

        @Column(name = "created_by")
        private String created_by;

        @Column(name = "created_date")
        private LocalDateTime created_date;

        @Column(name = "updated_by")
        private String updated_by;

        @Column(name = "updated_date")
        private LocalDateTime updated_date;

        @Column(precision = 10, scale = 2)
        private BigDecimal min_salary;
        @Column(precision = 10, scale = 2)
        private BigDecimal max_salary;


}