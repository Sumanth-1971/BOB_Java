package com.bob.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "templates")
public class Templates {


    @Id
    private Integer template_id;

    private String template_type;

    private String template_name;

    @Column(name = "template_desc", columnDefinition = "text")
    private String template_desc;

    @Column(name = "file_path", columnDefinition = "text")
    private String file_path;
    @Column(name = "others" , columnDefinition = "jsonb")
    private String others;

    private String created_by;

    private LocalDateTime created_date;

    private String updated_by;

    private LocalDateTime updated_date;


    public Templates() {
    }

    public Templates(Integer template_id, String template_type, String template_name, String template_desc, String file_path, String others, String created_by, LocalDateTime created_date, String updated_by, LocalDateTime updated_date) {
        this.template_id = template_id;
        this.template_type = template_type;
        this.template_name = template_name;
        this.template_desc = template_desc;
        this.file_path = file_path;
        this.others = others;
        this.created_by = created_by;
        this.created_date = created_date;
        this.updated_by = updated_by;
        this.updated_date = updated_date;
    }

    public Integer getTemplate_id() {
        return template_id;
    }
    public void setTemplate_id(Integer template_id) {
        this.template_id = template_id;
    }
    public String getTemplate_type() {
        return template_type;
    }
    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    public String getTemplate_name() {
        return template_name;
    }
    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getTemplate_desc() {
        return template_desc;
    }
    public void setTemplate_desc(String template_desc) {
        this.template_desc = template_desc;
    }
    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
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
