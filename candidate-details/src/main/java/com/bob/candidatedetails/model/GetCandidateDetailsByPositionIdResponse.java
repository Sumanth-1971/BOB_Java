package com.bob.candidatedetails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class GetCandidateDetailsByPositionIdResponse {

    @JsonProperty("candidate_id")
    private UUID candidateId;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("country_details")
    private Map<Long, String> countryDetails;

    @JsonProperty("state_details")
    private Map<Long, String> stateDetails;

    @JsonProperty("location_details")
    private Map<Long, String> locationDetails;

    @JsonProperty("city_details")
    private Map<Long, String> cityDetails;

    @JsonProperty("email")
    private String email;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("reservation_category_id")
    private Integer reservationCategoryId;

    @JsonProperty("special_category_id")
    private Integer specialCategoryId;

    @JsonProperty("highest_qualification")
    private Integer highestQualification;

    @JsonProperty("total_experience")
    private String totalExperience;

    @JsonProperty("address")
    private String address;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("application_status")
    private String applicationStatus;

    @JsonProperty("fileUrl")
    private String fileUrl;
}