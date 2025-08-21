package com.bob.db.entity;

import com.bob.db.dto.RazorpayDTO;

public class RazorpayOrdersResponse {
    RazorpayDTO razorpayOrderDetails;
    String candidateName;
    String candidateEmail;
    String candidateCurrentDesignation;
    String positionTitle;
    String positionDescription;

    public RazorpayDTO getRazorpayOrderDetails() {
        return razorpayOrderDetails;
    }

    public void setRazorpayOrderDetails(RazorpayDTO razorpayOrderDetails) {
        this.razorpayOrderDetails = razorpayOrderDetails;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidateCurrentDesignation() {
        return candidateCurrentDesignation;
    }

    public void setCandidateCurrentDesignation(String candidateCurrentDesignation) {
        this.candidateCurrentDesignation = candidateCurrentDesignation;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }
}
