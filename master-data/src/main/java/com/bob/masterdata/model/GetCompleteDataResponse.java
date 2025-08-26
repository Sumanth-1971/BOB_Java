package com.bob.masterdata.model;

import com.bob.db.dto.CityDto;
import com.bob.db.dto.LocationDto;
import com.bob.db.dto.StateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GetCompleteDataResponse {

    private List<String> position_title;

    private List<Map<Long,String>> departments;

    private List<Map<Long,String>> countries;
//
////    List<Map<Long,String>> states;
//    private List<Map<Long,Map<String,Long>>> states;
//    private List<Map<Long,Map<String,Long>>> cities;
//
//    private List<Map<Long,String>> locations;

    private List<LocationDto> locations;

    private List<CityDto> cities;

    private List<StateDto> states;

//    private String description;

    private List<Map<Long,String>> skills;

    private List<Map<Long,String>> job_grade_data;

    private List<Map<Long,String>> mandatory_qualification;

    private List<Map<Long,String>> preferred_qualification;
}
