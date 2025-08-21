package com.bob.db.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation_categories")
public class ReservationCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_categories_id;

    private String category_code;

    private String category_name;

    @Column(name="category_desc",columnDefinition = "text")
    private String category_desc;

    private LocalDateTime created_date;

    private String created_by;

    private LocalDateTime updated_date;

    private String updated_by;

    public ReservationCategories() {
    }

    public ReservationCategories(Long reservation_categories_id, String category_code, String category_name, String category_desc, LocalDateTime created_date, String created_by, LocalDateTime updated_date, String updated_by) {
        this.reservation_categories_id = reservation_categories_id;
        this.category_code = category_code;
        this.category_name = category_name;
        this.category_desc = category_desc;
        this.created_date = created_date;
        this.created_by = created_by;
        this.updated_date = updated_date;
        this.updated_by = updated_by;
    }

    public Long getReservation_categories_id() {
        return reservation_categories_id;
    }

    public void setReservation_categories_id(Long reservation_categories_id) {
        this.reservation_categories_id = reservation_categories_id;
    }

    public String getCategory_code() {
        return category_code;
    }

    public void setCategory_code(String category_code) {
        this.category_code = category_code;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(String category_desc) {
        this.category_desc = category_desc;
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
