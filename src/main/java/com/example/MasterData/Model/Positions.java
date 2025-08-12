package com.example.MasterData.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "positions")
public class Positions {

    @Id
    private UUID position_id;

    private String requistion_id;


    private String position_title;



}
