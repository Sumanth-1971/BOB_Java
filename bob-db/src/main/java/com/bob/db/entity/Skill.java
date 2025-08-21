package com.bob.db.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skill_id;

    private String skill_name;

    @Column(name="skill_desc",columnDefinition = "text")
    private String skill_desc;

    private LocalDate start_date;

    private LocalDate end_date;

    private LocalDateTime created_date;

    private String created_by;

    public Skill() {
    }

    public Skill(Long skill_id, String skill_name, String skill_desc, LocalDate start_date, LocalDate end_date, LocalDateTime created_date, String created_by) {
        this.skill_id = skill_id;
        this.skill_name = skill_name;
        this.skill_desc = skill_desc;
        this.start_date = start_date;
        this.end_date = end_date;
        this.created_date = created_date;
        this.created_by = created_by;
    }

    public Long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Long skill_id) {
        this.skill_id = skill_id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getSkill_desc() {
        return skill_desc;
    }

    public void setSkill_desc(String skill_desc) {
        this.skill_desc = skill_desc;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
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
