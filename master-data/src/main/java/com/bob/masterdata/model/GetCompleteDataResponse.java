package com.bob.masterdata.model;

import com.bob.db.dto.CityDto;
import com.bob.db.dto.LocationDto;
import com.bob.db.dto.StateDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GetCompleteDataResponse {

    @JsonProperty("position_title")
    private List<String> positionTitle;

    private List<Map<Long,String>> departments;

    private List<Map<Long,String>> countries;

    private List<LocationDto> locations;

    private List<CityDto> cities;

    private List<StateDto> states;


    private List<Map<Long,String>> skills;

    @JsonProperty("job_grade_data")
    private List<Map<Long,String>> jobGradeData;

    @JsonProperty("mandatory_qualification")
    private List<Map<Long,String>> mandatoryQualification;

    @JsonProperty("preferred_qualification")
    private List<Map<Long,String>> preferredQualification;
}
