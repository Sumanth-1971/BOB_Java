package com.example.MasterData.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_grade")
public class JobGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long job_grade_id;

    private String job_grade_code;

    private String job_grade_desc;

    private String job_scale;

    @Column(precision = 12, scale = 2)
    private BigDecimal min_salary;

    @Column(precision = 12, scale = 2)
    private BigDecimal max_salary;

    private LocalDate effective_state_date;

    private LocalDate effective_end_date;

    private LocalDateTime created_date;

    private String created_by;

    public JobGrade() {
    }

    public JobGrade(Long job_grade_id, String job_grade_code, String job_grade_desc, String job_scale, BigDecimal min_salary, BigDecimal max_salary, LocalDate effective_state_date, LocalDate effective_end_date, LocalDateTime created_date, String created_by) {
        this.job_grade_id = job_grade_id;
        this.job_grade_code = job_grade_code;
        this.job_grade_desc = job_grade_desc;
        this.job_scale = job_scale;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
        this.effective_state_date = effective_state_date;
        this.effective_end_date = effective_end_date;
        this.created_date = created_date;
        this.created_by = created_by;
    }

    public Long getJob_grade_id() {
        return job_grade_id;
    }

    public void setJob_grade_id(Long job_grade_id) {
        this.job_grade_id = job_grade_id;
    }

    public String getJob_grade_code() {
        return job_grade_code;
    }

    public void setJob_grade_code(String job_grade_code) {
        this.job_grade_code = job_grade_code;
    }

    public String getJob_grade_desc() {
        return job_grade_desc;
    }

    public void setJob_grade_desc(String job_grade_desc) {
        this.job_grade_desc = job_grade_desc;
    }

    public String getJob_scale() {
        return job_scale;
    }

    public void setJob_scale(String job_scale) {
        this.job_scale = job_scale;
    }

    public BigDecimal getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(BigDecimal min_salary) {
        this.min_salary = min_salary;
    }

    public BigDecimal getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(BigDecimal max_salary) {
        this.max_salary = max_salary;
    }

    public LocalDate getEffective_state_date() {
        return effective_state_date;
    }

    public void setEffective_state_date(LocalDate effective_state_date) {
        this.effective_state_date = effective_state_date;
    }

    public LocalDate getEffective_end_date() {
        return effective_end_date;
    }

    public void setEffective_end_date(LocalDate effective_end_date) {
        this.effective_end_date = effective_end_date;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
