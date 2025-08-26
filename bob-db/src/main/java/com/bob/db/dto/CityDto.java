package com.bob.db.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CityDto {

    @JsonProperty("city_id")
    private Long cityId;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("state_id")
    private Long stateId;

}
