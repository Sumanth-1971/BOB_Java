package com.bob.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "locations")
@Getter
@Setter
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="location_id",nullable = false)
    private Long locationId;

    @Column(name="location_name")
    private String locationName;

    @Column(name="city_id")
    private Long cityId;

    @Column(name="created_date", columnDefinition = "timestamp default now()")
    private LocalDateTime createdDate;


    @Column(name="created_by")
    private String createdBy;


}
