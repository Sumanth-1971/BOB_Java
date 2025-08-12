package com.example.MasterData.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GradeDto {
    private Long job_grade_id;

    private String job_scale;

//    @Column(precision = 12, scale = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private BigDecimal min_salary;

//    @Column(precision = 12, scale = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
    private BigDecimal max_salary;

    public GradeDto() {
    }

    public GradeDto(Long job_grade_id, String job_scale, BigDecimal min_salary, BigDecimal max_salary) {
        this.job_grade_id = job_grade_id;
        this.job_scale = job_scale;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }

    public Long getJob_grade_id() {
        return job_grade_id;
    }

    public void setJob_grade_id(Long job_grade_id) {
        this.job_grade_id = job_grade_id;
    }

    public String getJob_scale() {
        return job_scale;
    }

    public void setJob_scale(String job_scale) {
        this.job_scale = job_scale;
    }

    public String getMin_salary() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.min_salary);
    }

    public void setMin_salary(BigDecimal min_salary) {
        this.min_salary = min_salary;
    }

    public String getMax_salary() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.max_salary);
    }

    public void setMax_salary(BigDecimal max_salary) {
        this.max_salary = max_salary;
    }
}
