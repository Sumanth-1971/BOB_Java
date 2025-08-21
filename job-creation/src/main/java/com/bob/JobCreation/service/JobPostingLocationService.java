package com.bob.JobCreation.service;


import com.bob.db.repository.*;
import com.bob.db.dto.JobPositionsDTO;
import com.bob.db.entity.JobPostingLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobPostingLocationService {

//    private Integer posting_location_id ; -->default
//    private UUID position_id; --> set
//
//    private Integer dept_id; --> set
//    private Integer location_id; --> set

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private JobPostingLocationRepository jobPostingLocationRepository;

    private JobPostingLocation setValues(JobPositionsDTO jobPositionsDTO,UUID positionId){
        JobPostingLocation jobPostingLocation = new JobPostingLocation();

        jobPostingLocation.setLocation_id(jobPositionsDTO.getLocation_id());
        jobPostingLocation.setDept_id(jobPositionsDTO.getDept_id());
        jobPostingLocation.setPosition_id(positionId);

        return jobPostingLocation;
    }

    public JobPostingLocation createPostingLocation(JobPositionsDTO jobPositionsDTO,UUID positionId) {
        JobPostingLocation jobPostingLocation = setValues(jobPositionsDTO, positionId);
        return jobPostingLocationRepository.save(jobPostingLocation);
    }

    public JobPostingLocation getByPositionIdPostingLocation(UUID positionId) {
        return jobPostingLocationRepository.findByPositionId2(positionId);
    }

    public void updatePostingLocation(JobPositionsDTO jobPositionsDTO, UUID positionId) {
        JobPostingLocation existingLocation = jobPostingLocationRepository.findByPositionId2(positionId);
        if (existingLocation != null) {
            existingLocation.setDept_id(jobPositionsDTO.getDept_id());
            existingLocation.setLocation_id(jobPositionsDTO.getLocation_id());
            jobPostingLocationRepository.save(existingLocation);
        } else {
            throw new RuntimeException("Posting location not found for position ID: " + positionId);
        }
    }

    public Long findCityBylocationId(Long locationId) {
        return locationRepository.findcityIdByLocationId(locationId);
    }
    public Long findStateByCityId(Long cityId) {
        return cityRepository.findStateIdByCityId(cityId);
    }
    public Long findCountryByStateId(Long stateId) {
        return stateRepository.findCountryIdByStateId(stateId);
    }

}