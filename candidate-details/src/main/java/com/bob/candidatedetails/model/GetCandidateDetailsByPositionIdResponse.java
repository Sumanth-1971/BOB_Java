package com.bob.candidatedetails.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class GetCandidateDetailsByPositionIdResponse {
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
}


