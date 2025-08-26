package com.bob.db.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAnyAttribute;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "state")
@Getter
@Setter
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="state_id", nullable = false)
    private Long stateId;

    @Column(name="state_name")
    private String stateName;

    @Column(name="country_id")
    private Long countryId;

    @Column(name="created_date", columnDefinition = "timestamp default now()")
    private LocalDateTime createdDate;

    @Column(name="created_by")
    private String createdBy;
}
