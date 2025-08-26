package com.bob.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "city")
@Getter
@Setter
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id",nullable = false)
    private Long cityId;

    @Column(name="city_name")
    private String cityName;

    @Column(name="state_id")
    private Long stateId;

    @Column(name="created_date", columnDefinition = "timestamp default now()")
    private LocalDateTime createdDate;


    @Column(name="created_by")
    private String createdBy;

    }
