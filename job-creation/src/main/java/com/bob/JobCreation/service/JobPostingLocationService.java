package com.bob.JobCreation.service;


import com.bob.db.repository.*;
import com.bob.db.dto.JobPositionsDTO;
import com.bob.db.entity.JobPostingLocationEntity;
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

    private JobPostingLocationEntity setValues(JobPositionsDTO jobPositionsDTO, UUID positionId){
        JobPostingLocationEntity jobPostingLocation = new JobPostingLocationEntity();

        jobPostingLocation.setLocationId(jobPositionsDTO.getLocation_id());
        jobPostingLocation.setDeptId(jobPositionsDTO.getDept_id());
        jobPostingLocation.setPositionId(positionId);

        return jobPostingLocation;
    }

    public JobPostingLocationEntity createPostingLocation(JobPositionsDTO jobPositionsDTO, UUID positionId) {
        JobPostingLocationEntity jobPostingLocation = setValues(jobPositionsDTO, positionId);
        return jobPostingLocationRepository.save(jobPostingLocation);
    }

    public JobPostingLocationEntity getByPositionIdPostingLocation(UUID positionId) {
        return jobPostingLocationRepository.findByPositionId2(positionId);
    }

    public void updatePostingLocation(JobPositionsDTO jobPositionsDTO, UUID positionId) {
        JobPostingLocationEntity existingLocation = jobPostingLocationRepository.findByPositionId2(positionId);
        if (existingLocation != null) {
            existingLocation.setDeptId(jobPositionsDTO.getDept_id());
            existingLocation.setLocationId(jobPositionsDTO.getLocation_id());
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