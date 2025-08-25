package com.bob.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "job_posting_location")
@Getter
@Setter
public class JobPostingLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_location_id", nullable = false)
    private Integer postingLocationId ;

    @Column(name = "position_id", nullable = false)
    private UUID positionId;
    @Column(name = "dept_id", nullable = false)
    private Integer deptId;

    @Column(name = "location_id", nullable = false)
    private Long locationId;

}
