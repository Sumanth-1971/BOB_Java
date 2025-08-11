package com.example.CandidateDetails.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidates")
public class Candidates {

    @Id
    private UUID candidate_id;

    private String full_name;

    private String username;

    private String password_hash;

    private String email;

    private String phone;

    private LocalDate date_of_birth;

    private String gender;

    private String id_proof;

    private Integer nationality_id;

    private Integer reservation_category_id;

    private Integer special_category_id;

    private Integer highest_qualification_id;
    @Column(precision = 4,scale = 1)
    private BigDecimal total_experience;

    private String address;

    private String comments;

    LocalDateTime created_date;


    public Candidates() {
    }

    public Candidates(UUID candidate_id, String full_name, String username, String password_hash, String email, String phone, LocalDate date_of_birth, String gender, String id_proof, Integer nationality_id, Integer reservation_category_id, Integer special_category_id, Integer highest_qualification_id, BigDecimal total_experience, String address, String comments, LocalDateTime created_date) {
        this.candidate_id = candidate_id;
        this.full_name = full_name;
        this.username = username;
        this.password_hash = password_hash;
        this.email = email;
        this.phone = phone;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.id_proof = id_proof;
        this.nationality_id = nationality_id;
        this.reservation_category_id = reservation_category_id;
        this.special_category_id = special_category_id;
        this.highest_qualification_id = highest_qualification_id;
        this.total_experience = total_experience;
        this.address = address;
        this.comments = comments;
        this.created_date = created_date;
    }

    public UUID getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(UUID candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId_proof() {
        return id_proof;
    }

    public void setId_proof(String id_proof) {
        this.id_proof = id_proof;
    }

    public Integer getNationality_id() {
        return nationality_id;
    }

    public void setNationality_id(Integer nationality_id) {
        this.nationality_id = nationality_id;
    }

    public Integer getReservation_category_id() {
        return reservation_category_id;
    }

    public void setReservation_category_id(Integer reservation_category_id) {
        this.reservation_category_id = reservation_category_id;
    }

    public Integer getSpecial_category_id() {
        return special_category_id;
    }

    public void setSpecial_category_id(Integer special_category_id) {
        this.special_category_id = special_category_id;
    }

    public Integer getHighest_qualification_id() {
        return highest_qualification_id;
    }

    public void setHighest_qualification_id(Integer highest_qualification_id) {
        this.highest_qualification_id = highest_qualification_id;
    }

    public BigDecimal getTotal_experience() {
        return total_experience;
    }

    public void setTotal_experience(BigDecimal total_experience) {
        this.total_experience = total_experience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }
}
