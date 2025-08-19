package com.example.MasterData.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "special_categories")
public class SpecialCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long special_category_id;

    private String special_category_code;

    private String special_category_name;

    @Column(name = "special_category_desc", columnDefinition = "text")
    private String special_category_desc;

    private LocalDateTime created_date;

    private String created_by;

    private LocalDateTime updated_date;

    private String updated_by;

    public SpecialCategories() {
    }

    public SpecialCategories(Long special_category_id, String special_category_code, String special_category_name, String special_category_desc, LocalDateTime created_date, String created_by, LocalDateTime updated_date, String updated_by) {
        this.special_category_id = special_category_id;
        this.special_category_code = special_category_code;
        this.special_category_name = special_category_name;
        this.special_category_desc = special_category_desc;
        this.created_date = created_date;
        this.created_by = created_by;
        this.updated_date = updated_date;
        this.updated_by = updated_by;
    }

    public Long getSpecial_category_id() {
        return special_category_id;
    }

    public void setSpecial_category_id(Long special_category_id) {
        this.special_category_id = special_category_id;
    }

    public String getSpecial_category_code() {
        return special_category_code;
    }

    public void setSpecial_category_code(String special_category_code) {
        this.special_category_code = special_category_code;
    }

    public String getSpecial_category_name() {
        return special_category_name;
    }

    public void setSpecial_category_name(String special_category_name) {
        this.special_category_name = special_category_name;
    }

    public String getSpecial_category_desc() {
        return special_category_desc;
    }

    public void setSpecial_category_desc(String special_category_desc) {
        this.special_category_desc = special_category_desc;
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
