package com.example.CandidateDetails.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CandidateDetails {

    private UUID candidate_id;

    private String full_name;

    private String username;

//    private Map<Integer,String> country_details;
//
//    private Map<Integer,String> state_details;

    private String email;

    private String gender;

    private String phone;

    private Integer reservation_category_id;

    private Integer special_category_id;

    private Integer highest_qualification;

    private BigDecimal total_experience;

    private String address;

    private String comments;

    private String application_status;

    private List<FileInfo> fileInfo;


    public CandidateDetails() {
    }

//    public CandidateDetails(UUID candidate_id, String full_name, String username, Map<Integer, String> country_details, Map<Integer, String> state_details, String email, String gender, String phone, Integer reservation_category_id, Integer special_category_id, Integer highest_qualification, BigDecimal total_experience, String address, String comments, String application_status, List<FileInfo> fileInfo) {
//        this.candidate_id = candidate_id;
//        this.full_name = full_name;
//        this.username = username;
//        this.country_details = country_details;
//        this.state_details = state_details;
//        this.email = email;
//        this.gender = gender;
//        this.phone = phone;
//        this.reservation_category_id = reservation_category_id;
//        this.special_category_id = special_category_id;
//        this.highest_qualification = highest_qualification;
//        this.total_experience = total_experience;
//        this.address = address;
//        this.comments = comments;
//        this.application_status = application_status;
//        this.fileInfo = fileInfo;
//    }


    public CandidateDetails(UUID candidate_id, String full_name, String username, String email, String gender, String phone, Integer reservation_category_id, Integer special_category_id, Integer highest_qualification, BigDecimal total_experience, String address, String comments, String application_status, List<FileInfo> fileInfo) {
        this.candidate_id = candidate_id;
        this.full_name = full_name;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.reservation_category_id = reservation_category_id;
        this.special_category_id = special_category_id;
        this.highest_qualification = highest_qualification;
        this.total_experience = total_experience;
        this.address = address;
        this.comments = comments;
        this.application_status = application_status;
        this.fileInfo = fileInfo;
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

//    public Map<Integer, String> getCountry_details() {
//        return country_details;
//    }
//
//    public void setCountry_details(Map<Integer, String> country_details) {
//        this.country_details = country_details;
//    }
//
//    public Map<Integer, String> getState_details() {
//        return state_details;
//    }
//
//    public void setState_details(Map<Integer, String> state_details) {
//        this.state_details = state_details;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getHighest_qualification() {
        return highest_qualification;
    }

    public void setHighest_qualification(Integer highest_qualification) {
        this.highest_qualification = highest_qualification;
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

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
    }

    public List<FileInfo> getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(List<FileInfo> fileInfo) {
        this.fileInfo = fileInfo;
    }
}
