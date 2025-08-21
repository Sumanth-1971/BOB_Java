package com.bob.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "positions")
public class Position {


    @Id
    private UUID position_id;

    @Column(name = "requisition_id", columnDefinition = "uuid")
    private String requisition_id;

    private String position_title;


    public Position() {
    }

    public Position(UUID position_id, String requisition_id, String position_title) {
        this.position_id = position_id;
        this.requisition_id = requisition_id;
        this.position_title = position_title;
    }
    public String getRequisition_id() {
        return requisition_id;
    }
    public void setRequisition_id(String requisition_id) {
        this.requisition_id = requisition_id;
    }

    public UUID getPosition_id() {
        return position_id;
    }

    public void setPosition_id(UUID position_id) {
        this.position_id = position_id;
    }



    public String getPosition_title() {
        return position_title;
    }

    public void setPosition_title(String position_title) {
        this.position_title = position_title;
    }
}
