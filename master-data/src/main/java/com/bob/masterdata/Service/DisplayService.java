package com.bob.masterdata.Service;

import com.bob.db.entity.ReservationCategories;
import com.bob.db.mapper.CityMapper;
import com.bob.db.mapper.StateMapper;
import com.bob.db.repository.*;
//import com.bob.db.dto.MasterDTO;
import com.bob.db.dto.CityDto;
import com.bob.db.dto.LocationDto;
import com.bob.db.dto.MasterDTO2;
import com.bob.db.dto.StateDto;
import com.bob.masterdata.model.GetCompleteDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DisplayService {

    @Autowired
    private ReservationCategoriesRepository reservationCategories;
    @Autowired
    private CityRepository cityRepository;


    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Autowired
    private EducationalQualificationsRepository educationalQualificationsRepository;

    @Autowired
    private JobGradeRepository jobGradeRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SpecialCategoriesRepository specialCategoriesRepository;

    @Autowired
    private StateMapper stateMapper;
    @Autowired
    private StateRepository stateRepository;

    public List<ReservationCategories> getAllCategories(){
        return reservationCategories.findAll();
    }

    public Map<String,List<?>> getAllData(){
        Map<String,List<?>> mp=new HashMap<>();
        mp.put("City", cityRepository.findAll());
        mp.put("Country",countryRepository.findAll());
        mp.put("Departments",departmentsRepository.findAll());
        mp.put("Educational Qualifications",educationalQualificationsRepository.findAll());
        mp.put("Job Grade",jobGradeRepository.findAll());
        mp.put("Location",locationRepository.findAll());
        mp.put("Reservation Categories",reservationCategories.findAll());
        mp.put("Skills", skillRepository.findAll());
        mp.put("Special Categories",specialCategoriesRepository.findAll());
        mp.put("State",stateRepository.findAll());
        return mp;
    }



    public GetCompleteDataResponse getAllData3(){
        GetCompleteDataResponse getCompleteDataResponse =new GetCompleteDataResponse();
        List<String> position_title=departmentsRepository.getDepartmentNames();
        List<Map<Long,String>> department=departmentsRepository.getData();
        List<Map<Long,String>> countries=countryRepository.getData();
//        List<StateDto> states=stateRepository.getData();
        List<StateDto> states=stateMapper.toDtoList(stateRepository.findAll());
        List<CityDto> cities=cityMapper.toDtoList(cityRepository.findAll());
//        System.out.println(cities.toString());
        List<LocationDto> locations=locationRepository.getData();
        List<Map<Long,String>> skills=skillRepository.getSkillIdDescriptions();
        List<Map<Long,String>> job_Data=jobGradeRepository.getData();
        List<Map<Long,String>> mandatory= educationalQualificationsRepository.getData();
        List<Map<Long,String>> preferred= educationalQualificationsRepository.getData();
        getCompleteDataResponse.setPosition_title(position_title);
        getCompleteDataResponse.setDepartments(department);
        getCompleteDataResponse.setCountries(countries);
        getCompleteDataResponse.setStates(states);
        getCompleteDataResponse.setCities(cities);
        getCompleteDataResponse.setLocations(locations);
        getCompleteDataResponse.setSkills(skills);
        getCompleteDataResponse.setJob_grade_data(job_Data);
        getCompleteDataResponse.setMandatory_qualification(mandatory);
        getCompleteDataResponse.setPreferred_qualification(preferred);
        return getCompleteDataResponse;

    }


}
