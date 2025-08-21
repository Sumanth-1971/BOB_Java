package com.bob.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @Column(name = "userid")
    private Long userid;

    private String name;
    private String role;
    private String email;
    private LocalDateTime created_date;
    private String created_by;
    private LocalDateTime updated_date;
    private String updated_by;
    private Long manager_id;
    private Integer step_level;
    private String user_password;

    // Getters and setters
}