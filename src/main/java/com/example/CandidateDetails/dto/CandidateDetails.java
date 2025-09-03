package com.example.CandidateDetails.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CandidateDetails {

    private UUID candidate_id;

    private String full_name;

    private String username;

    private Map<Long,String> country_details;

    private Map<Long,String> state_details;

    private Map<Long,String> location_details;

    private Map<Long,String> city_details;

    private String email;

    private String gender;

    private String phone;

    private Integer reservation_category_id;

    private Integer special_category_id;

    private Integer highest_qualification;

    private String total_experience;

    private String address;

    private String comments;

    private String application_status;

    private String fileUrl;

    @JsonProperty("document_url")
    private String documentUrl;

    private String offerLetterUrl;






    private LocalDate date_of_birth;

    private String id_proof;

    private Integer nationality_id;


    private Integer highest_qualification_id;

    private String skills;

    private String current_designation;

    private String current_employer;

    private String file_url;

    private String education_qualification;

    private Integer rank;





    public CandidateDetails() {
    }

    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public CandidateDetails(UUID candidate_id, String full_name, String username, Map<Long, String> country_details, Map<Long, String> state_details, Map<Long, String> location_details, Map<Long, String> city_details, String email, String gender, String phone, Integer reservation_category_id, Integer special_category_id, Integer highest_qualification, String total_experience, String address, String comments, String application_status, String fileUrl, String documentUrl, String offerLetterUrl, LocalDate date_of_birth, String id_proof, Integer nationality_id, Integer highest_qualification_id, String skills, String current_designation, String current_employer, String file_url, String education_qualification, Integer rank) {
        this.candidate_id = candidate_id;
        this.full_name = full_name;
        this.username = username;
        this.country_details = country_details;
        this.state_details = state_details;
        this.location_details = location_details;
        this.city_details = city_details;
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
        this.fileUrl = fileUrl;
        this.documentUrl = documentUrl;
        this.offerLetterUrl = offerLetterUrl;
        this.date_of_birth = date_of_birth;
        this.id_proof = id_proof;
        this.nationality_id = nationality_id;
        this.highest_qualification_id = highest_qualification_id;
        this.skills = skills;
        this.current_designation = current_designation;
        this.current_employer = current_employer;
        this.file_url = file_url;
        this.education_qualification = education_qualification;
        this.rank = rank;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    public Integer getHighest_qualification_id() {
        return highest_qualification_id;
    }

    public void setHighest_qualification_id(Integer highest_qualification_id) {
        this.highest_qualification_id = highest_qualification_id;
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

    public String getDocumentUrl() {
        return documentUrl;
    }
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
    public String getOfferLetterUrl() {
        return offerLetterUrl;
    }
    public void setOfferLetterUrl(String offerLetterUrl) {
        this.offerLetterUrl = offerLetterUrl;
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

    public Map<Long, String> getCountry_details() {
        return country_details;
    }

    public void setCountry_details(Map<Long, String> country_details) {
        this.country_details = country_details;
    }

    public Map<Long, String> getState_details() {
        return state_details;
    }

    public void setState_details(Map<Long, String> state_details) {
        this.state_details = state_details;
    }

    public Map<Long, String> getLocation_details() {
        return location_details;
    }

    public void setLocation_details(Map<Long, String> location_details) {
        this.location_details = location_details;
    }

    public Map<Long, String> getCity_details() {
        return city_details;
    }

    public void setCity_details(Map<Long, String> city_details) {
        this.city_details = city_details;
    }

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

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
