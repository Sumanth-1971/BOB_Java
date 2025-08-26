package com.bob.db.dto;

import com.bob.db.entity.Candidates;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.UUID;

public class CandidatesDto {
    private UUID candidate_id;

    private String full_name;


    private String email;

    private String phone;

    private LocalDate date_of_birth;

    private String gender;

    private String id_proof;

    private Integer nationality_id;

    private Integer reservation_category_id;

    private Integer special_category_id;

    private Integer highest_qualification_id;

    @Column
    private String total_experience;

    private String address;

    private String comments;

    private String skills;

    private String current_designation;
    private String current_employer;
    private String file_url;
    private String education_qualification;

    public CandidatesDto() {
    }

    public CandidatesDto(UUID candidate_id, String full_name, String email, String phone, LocalDate date_of_birth, String gender, String id_proof, Integer nationality_id, Integer reservation_category_id, Integer special_category_id, Integer highest_qualification_id, String total_experience, String address, String comments, String skills, String current_designation, String current_employer, String file_url, String education_qualification) {
        this.candidate_id = candidate_id;
        this.full_name = full_name;
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
        this.skills = skills;
        this.current_designation = current_designation;
        this.current_employer = current_employer;
        this.file_url = file_url;
        this.education_qualification = education_qualification;
    }

    public CandidatesDto(Candidates updatedCandidate) {
        this.candidate_id = updatedCandidate.getCandidate_id();
        this.full_name = updatedCandidate.getFull_name();
        this.email = updatedCandidate.getEmail();
        this.phone = updatedCandidate.getPhone();
        this.date_of_birth = updatedCandidate.getDate_of_birth();
        this.gender = updatedCandidate.getGender();
        this.id_proof = updatedCandidate.getId_proof();
        this.nationality_id = updatedCandidate.getNationality_id();
        this.reservation_category_id = updatedCandidate.getReservation_category_id();
        this.special_category_id = updatedCandidate.getSpecial_category_id();
        this.highest_qualification_id = updatedCandidate.getHighest_qualification_id();
        this.total_experience = updatedCandidate.getTotal_experience();
        this.address = updatedCandidate.getAddress();
        this.comments = updatedCandidate.getComments();
        this.skills = updatedCandidate.getSkills();
        this.current_designation = updatedCandidate.getCurrent_designation();
        this.current_employer = updatedCandidate.getCurrent_employer();
        this.file_url = updatedCandidate.getFile_url();
        this.education_qualification = updatedCandidate.getEducation_qualification();
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

    public String getTotal_experience() {
        return total_experience;
    }
    public void setTotal_experience(String total_experience) {
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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCurrent_designation() {
        return current_designation;
    }

    public void setCurrent_designation(String current_designation) {
        this.current_designation = current_designation;
    }

    public String getCurrent_employer() {
        return current_employer;
    }

    public void setCurrent_employer(String current_employer) {
        this.current_employer = current_employer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public String getEducation_qualification() {
        return education_qualification;
    }

    public void setEducation_qualification(String education_qualification) {
        this.education_qualification = education_qualification;
    }
}
