package com.bob.db.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "education_qualifications")
public class EducationalQualifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long edu_qualification_id;

    private String edu_qualification_name;

    @Column(name="edu_desc",columnDefinition = "text")
    private String edu_desc;

    private LocalDateTime created_date;

    private String created_by;

    private LocalDateTime updated_date;

    private String updated_by;

    public EducationalQualifications() {
    }


    public EducationalQualifications(Long edu_qualification_id, String edu_qualification_name, String edu_desc, LocalDateTime created_date, String created_by, LocalDateTime updated_date, String updated_by) {
        this.edu_qualification_id = edu_qualification_id;
        this.edu_qualification_name = edu_qualification_name;
        this.edu_desc = edu_desc;
        this.created_date = created_date;
        this.created_by = created_by;
        this.updated_date = updated_date;
        this.updated_by = updated_by;
    }

    public Long getEdu_qualification_id() {
        return edu_qualification_id;
    }

    public void setEdu_qualification_id(Long edu_qualification_id) {
        this.edu_qualification_id = edu_qualification_id;
    }

    public String getEdu_qualification_name() {
        return edu_qualification_name;
    }

    public void setEdu_qualification_name(String edu_qualification_name) {
        this.edu_qualification_name = edu_qualification_name;
    }

    public String getEdu_desc() {
        return edu_desc;
    }

    public void setEdu_desc(String edu_desc) {
        this.edu_desc = edu_desc;
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

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDateTime updated_date) {
        this.updated_date = updated_date;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
}
