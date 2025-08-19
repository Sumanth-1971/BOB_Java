package com.bob.JobCreation.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long city_id;

    private String city_name;

    private Long state_id;

    private LocalDateTime created_date;


    private String created_by;
    public City() {
    }
    public City(Long city_id, String city_name, Long state_id, LocalDateTime created_date, String created_by) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.state_id = state_id;
        this.created_date = created_date;
        this.created_by = created_by;
    }
    public Long getCity_id() {
        return city_id;
    }
    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }
    public String getCity_name() {
        return city_name;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
    public Long getState_id() {
        return state_id;
    }
    public void setState_id(Long state_id) {
        this.state_id = state_id;
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