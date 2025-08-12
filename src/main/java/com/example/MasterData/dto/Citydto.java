package com.example.MasterData.dto;

public class Citydto {

    private Long city_id;

    private String city_name;

    private Long state_id;

    public Citydto() {
    }

    public Citydto(Long city_id, String city_name, Long state_id) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.state_id = state_id;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Long getState_id() {
        return state_id;
    }

    public void setState_id(Long state_id) {
        this.state_id = state_id;
    }
}
