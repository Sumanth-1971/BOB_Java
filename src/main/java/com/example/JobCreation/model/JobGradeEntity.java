package com.example.JobCreation.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_grade")
@Data
public class JobGradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_grade_id")
    private Long jobGradeId;

    @Column(name = "job_grade_code")
    private String jobGradeCode;

    @Column(name = "job_grade_desc", columnDefinition = "text")
    private String jobGradeDesc;

    @Column(name = "job_scale")
    private String jobScale;

    @Column(name = "min_salary", precision = 12, scale = 2)
    private BigDecimal minSalary;

    @Column(name = "max_salary", precision = 12, scale = 2)
    private BigDecimal maxSalary;

    @Column(name = "effective_state_date")
    private LocalDate effectiveStateDate;

    @Column(name = "effective_end_date")
    private LocalDate effectiveEndDate;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private String createdBy;

}
