package com.bob.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "job_selection_process")
@Setter
@Getter
public class JobSelectionProcessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_selection_id", updatable = false, nullable = false)
    private Integer jobSelectionId;
    @Column(name = "position_id", nullable = false)
    private UUID position_id;
    @Column(name = "selection_procedure", columnDefinition = "text")
    private String selectionProcedure;
}