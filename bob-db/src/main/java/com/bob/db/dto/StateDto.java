package com.bob.db.dto;

public class StateDto {

    private Long stateId;

    private String stateName;

    private Long countryId;

    public StateDto() {
    }

    public StateDto(Long stateId, String stateName, Long countryId) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.countryId = countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
