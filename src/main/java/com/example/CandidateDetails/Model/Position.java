package com.example.CandidateDetails.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "positions")
public class Position {


    @Id
    private UUID position_id;

    private String requistion_id;

    private String position_title;


    public Position() {
    }

    public Position(UUID position_id, String requistion_id, String position_title) {
        this.position_id = position_id;
        this.requistion_id = requistion_id;
        this.position_title = position_title;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }

    public String getRequistion_id() {
        return requistion_id;
    }

    public void setRequistion_id(String requistion_id) {
        this.requistion_id = requistion_id;
    }

    public String getPosition_title() {
        return position_title;
    }

    public void setPosition_title(String position_title) {
        this.position_title = position_title;
    }
}
