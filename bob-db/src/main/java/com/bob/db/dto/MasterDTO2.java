package com.bob.db.dto;

import java.util.List;
import java.util.Map;

public class MasterDTO2 {


    private List<String> position_title;

    private List<Map<Long,String>> departments;

    private List<Map<Long,String>> countries;
//
////    List<Map<Long,String>> states;
//    private List<Map<Long,Map<String,Long>>> states;
//    private List<Map<Long,Map<String,Long>>> cities;
//
//    private List<Map<Long,String>> locations;

    private List<Locationdto> locations;

    private List<Citydto> cities;

    private List<StateDto> states;

//    private String description;

    private List<Map<Long,String>> skills;

    private List<Map<Long,String>> job_grade_data;

    private List<Map<Long,String>> mandatory_qualification;

    private List<Map<Long,String>> preferred_qualification;

    public MasterDTO2() {
    }

    public MasterDTO2(List<String> position_title, List<Map<Long, String>> departments, List<Map<Long, String>> countries, List<Locationdto> locations, List<Citydto> cities, List<StateDto> states, List<Map<Long, String>> skills, List<Map<Long, String>> job_grade_data, List<Map<Long, String>> mandatory_qualification, List<Map<Long, String>> preferred_qualification) {
        this.position_title = position_title;
        this.departments = departments;
        this.countries = countries;
        this.locations = locations;
        this.cities = cities;
        this.states = states;
        this.skills = skills;
        this.job_grade_data = job_grade_data;
        this.mandatory_qualification = mandatory_qualification;
        this.preferred_qualification = preferred_qualification;
    }

    public List<String> getPosition_title() {
        return position_title;
    }

    public void setPosition_title(List<String> position_title) {
        this.position_title = position_title;
    }

    public List<Map<Long, String>> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Map<Long, String>> departments) {
        this.departments = departments;
    }

    public List<Map<Long, String>> getCountries() {
        return countries;
    }

    public void setCountries(List<Map<Long, String>> countries) {
        this.countries = countries;
    }

    public List<Locationdto> getLocations() {
        return locations;
    }

    public void setLocations(List<Locationdto> locations) {
        this.locations = locations;
    }

    public List<Citydto> getCities() {
        return cities;
    }

    public void setCities(List<Citydto> cities) {
        this.cities = cities;
    }

    public List<StateDto> getStates() {
        return states;
    }

    public void setStates(List<StateDto> states) {
        this.states = states;
    }

    public List<Map<Long, String>> getSkills() {
        return skills;
    }

    public void setSkills(List<Map<Long, String>> skills) {
        this.skills = skills;
    }

    public List<Map<Long, String>> getJob_grade_data() {
        return job_grade_data;
    }

    public void setJob_grade_data(List<Map<Long, String>> job_grade_data) {
        this.job_grade_data = job_grade_data;
    }

    public List<Map<Long, String>> getMandatory_qualification() {
        return mandatory_qualification;
    }

    public void setMandatory_qualification(List<Map<Long, String>> mandatory_qualification) {
        this.mandatory_qualification = mandatory_qualification;
    }

    public List<Map<Long, String>> getPreferred_qualification() {
        return preferred_qualification;
    }

    public void setPreferred_qualification(List<Map<Long, String>> preferred_qualification) {
        this.preferred_qualification = preferred_qualification;
    }

    @Override
    public String toString() {
        return "MasterDTO2{" +
                "position_title=" + position_title +
                ", departments=" + departments +
                ", countries=" + countries +
                ", states=" + states +
                ", cities=" + cities +
                ", locations=" + locations +
                ", skills=" + skills +
                ", job_grade_data=" + job_grade_data +
                ", mandatory_qualification=" + mandatory_qualification +
                ", preferred_qualification=" + preferred_qualification +
                '}';
    }
}
