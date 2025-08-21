package com.bob.db.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "job_selection_process")
public class JobSelectionProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer job_selection_id;
    private UUID position_id;
    private String selection_procedure;

    public JobSelectionProcess() {
    }
    public JobSelectionProcess(Integer job_selection_id, UUID position_id, String selection_procedure) {
        this.job_selection_id = job_selection_id;
        this.position_id = position_id;
        this.selection_procedure = selection_procedure;
    }

    public Integer getJob_selection_id() {
        return job_selection_id;
    }

    public void setJob_selection_id(Integer job_selection_id) {
        this.job_selection_id = job_selection_id;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public String getSelection_procedure() {
        return selection_procedure;
    }

    public void setSelection_procedure(String selection_procedure) {
        this.selection_procedure = selection_procedure;
    }
}