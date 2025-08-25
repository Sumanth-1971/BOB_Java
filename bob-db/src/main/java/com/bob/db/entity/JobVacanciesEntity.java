package com.bob.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "job_vacancies")
@Setter
@Getter
public class JobVacanciesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_vacancy_id", updatable = false, nullable = false)
    private Integer jobVacancyId;

    @Column(name = "position_id", nullable = false)
    private UUID positionId;

    @Column(name = "special_cat_id", nullable = false)
    private Integer specialCatId;

    @Column(name = "reservation_cat_id", nullable = false)
    private Integer reservationCatId;

    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @Column(name = "no_of_vacancies", nullable = false)
    private Integer noOfVacancies;
}
