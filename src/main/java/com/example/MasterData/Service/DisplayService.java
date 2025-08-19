package com.example.MasterData.Service;

import com.example.MasterData.Model.Country;
import com.example.MasterData.Model.ReservationCategories;
import com.example.MasterData.Repository.*;
//import com.example.MasterData.dto.MasterDTO;
import com.example.MasterData.dto.Citydto;
import com.example.MasterData.dto.Locationdto;
import com.example.MasterData.dto.MasterDTO2;
import com.example.MasterData.dto.StateDto;
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



    public MasterDTO2 getAllData3(){
        List<String> position_title=departmentsRepository.getDepartmentNames();
        List<Map<Long,String>> department=departmentsRepository.getData();

        List<Map<Long,String>> countries=countryRepository.getData();
        List<StateDto> states=stateRepository.getData();
        List<Citydto> cities=cityRepository.getData();
        List<Locationdto> locations=locationRepository.getData();
        List<Map<Long,String>> skills=skillRepository.getSkillIdDescriptions();
        List<Map<Long,String>> job_Data=jobGradeRepository.getData();
        List<Map<Long,String>> mandatory= educationalQualificationsRepository.getData();
        List<Map<Long,String>> preferred= educationalQualificationsRepository.getData();
//        System.out.println(position_title+" "+department+" "+countries+" "+states+" "+locations+" "+skills+" "+job_Data+" "+cities);
        MasterDTO2 masterDTO2=new MasterDTO2(position_title,department,countries,locations,cities,states,skills,job_Data,mandatory,preferred);
        System.out.println(masterDTO2.toString());
        return masterDTO2;
    }


}
