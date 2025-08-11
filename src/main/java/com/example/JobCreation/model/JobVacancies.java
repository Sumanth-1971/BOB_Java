package com.example.JobCreation.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "job_vacancies")
public class JobVacancies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer job_vacancy_id;

    private UUID position_id;

    private Integer special_cat_id;
    private Integer reservation_cat_id;
    private Integer location_id;
    private Integer no_of_vacancies;

    public JobVacancies() {
    }

    public JobVacancies(Integer job_vacancy_id, UUID position_id, Integer special_cat_id, Integer reservation_cat_id, Integer location_id, Integer no_of_vacancies) {
        this.job_vacancy_id = job_vacancy_id;
        this.position_id = position_id;
        this.special_cat_id = special_cat_id;
        this.reservation_cat_id = reservation_cat_id;
        this.location_id = location_id;
        this.no_of_vacancies = no_of_vacancies;
    }

    public Integer getJob_vacancy_id() {
        return job_vacancy_id;
    }

    public void setJob_vacancy_id(Integer job_vacancy_id) {
        this.job_vacancy_id = job_vacancy_id;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public Integer getSpecial_cat_id() {
        return special_cat_id;
    }

    public void setSpecial_cat_id(Integer special_cat_id) {
        this.special_cat_id = special_cat_id;
    }

    public Integer getReservation_cat_id() {
        return reservation_cat_id;
    }

    public void setReservation_cat_id(Integer reservation_cat_id) {
        this.reservation_cat_id = reservation_cat_id;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public Integer getNo_of_vacancies() {
        return no_of_vacancies;
    }

    public void setNo_of_vacancies(Integer no_of_vacancies) {
        this.no_of_vacancies = no_of_vacancies;
    }
}

