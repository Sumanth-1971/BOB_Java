package com.bob.candidatedetails.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long country_id;

    private String country_name;

    private LocalDateTime created_date;

    private String created_by;


    public Country() {
    }

    public Country(Long country_id, String country_name, LocalDateTime created_date, String created_by) {
        this.country_id = country_id;
        this.country_name = country_name;
        this.created_date = created_date;
        this.created_by = created_by;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
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

    @Override
    public String toString() {
        return "Country{" +
                "country_id=" + country_id +
                ", country_name='" + country_name + '\'' +
                ", created_date=" + created_date +
                ", created_by='" + created_by + '\'' +
                '}';
    }
}
