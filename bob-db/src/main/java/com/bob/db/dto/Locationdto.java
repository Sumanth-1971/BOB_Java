package com.bob.db.dto;

public class Locationdto {

    private Long location_id;

    private String location_name;

    private Long city_id;

    public Locationdto() {
    }

    public Locationdto(Long location_id, String location_name, Long city_id) {
        this.location_id = location_id;
        this.location_name = location_name;
        this.city_id = city_id;
    }

    public Long getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Long location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }
}
