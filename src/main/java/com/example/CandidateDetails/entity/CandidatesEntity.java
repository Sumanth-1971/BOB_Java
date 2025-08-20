package com.example.CandidateDetails.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidates")
public class CandidatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "candidate_id", nullable = false, columnDefinition = "uuid")
    private UUID candidateId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String email;

    @Column
    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column
    private String gender;

    @Column(name = "id_proof")
    private String idProof;

    @Column(name = "nationality_id")
    private Integer nationalityId;

    @Column(name = "reservation_category_id")
    private Integer reservationCategoryId;

    @Column(name = "special_category_id")
    private Integer specialCategoryId;

    @Column(name = "highest_qualification_id")
    private Integer highestQualificationId;

    @Column(name = "total_experience", columnDefinition = "text")
    private String totalExperience;

    @Column(columnDefinition = "text")
    private String address;

    @Column(name = "comments", columnDefinition = "text")
    private String comments;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(columnDefinition = "text")
    private String skills;

    @Column(name = "current_designation")
    private String currentDesignation;

    @Column(name = "current_employer")
    private String currentEmployer;

    @Column(name = "education_qualification")
    private String educationQualification;

    @Column(name = "file_url", columnDefinition = "text")
    private String fileUrl;

    public CandidatesEntity() {
    }

    public UUID getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Integer getReservationCategoryId() {
        return reservationCategoryId;
    }

    public void setReservationCategoryId(Integer reservationCategoryId) {
        this.reservationCategoryId = reservationCategoryId;
    }

    public Integer getSpecialCategoryId() {
        return specialCategoryId;
    }

    public void setSpecialCategoryId(Integer specialCategoryId) {
        this.specialCategoryId = specialCategoryId;
    }

    public Integer getHighestQualificationId() {
        return highestQualificationId;
    }

    public void setHighestQualificationId(Integer highestQualificationId) {
        this.highestQualificationId = highestQualificationId;
    }

    public String getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(String totalExperience) {
        this.totalExperience = totalExperience;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCurrentDesignation() {
        return currentDesignation;
    }

    public void setCurrentDesignation(String currentDesignation) {
        this.currentDesignation = currentDesignation;
    }

    public String getCurrentEmployer() {
        return currentEmployer;
    }

    public void setCurrentEmployer(String currentEmployer) {
        this.currentEmployer = currentEmployer;
    }

    public String getEducationQualification() {
        return educationQualification;
    }

    public void setEducationQualification(String educationQualification) {
        this.educationQualification = educationQualification;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
