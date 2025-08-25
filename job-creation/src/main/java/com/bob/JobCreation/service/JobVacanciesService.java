package com.bob.JobCreation.service;

import com.bob.db.dto.JobPositionsDTO;
import com.bob.db.entity.JobVacanciesEntity;
import com.bob.db.repository.JobVacanciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JobVacanciesService {

//    private Integer job_vacancy_id; --> Default
//    private UUID position_id; --> set
//    private Integer special_cat_id; --> set
//    private Integer reservation_cat_id; --> set
//    private Integer location_id; --> set
//    private Integer no_of_vacancies; --> set

    @Autowired
    private JobVacanciesRepository jobVacanciesRepository;

    private JobVacanciesEntity setValues(JobPositionsDTO jobPositionsDTO, UUID position_id){
        JobVacanciesEntity jobVacancies = new JobVacanciesEntity();
        jobVacancies.setPositionId(position_id);

        jobVacancies.setNoOfVacancies(jobPositionsDTO.getNo_of_vacancies());
        jobVacancies.setLocationId(jobPositionsDTO.getLocation_id());
        jobVacancies.setReservationCatId(jobPositionsDTO.getReservation_cat_id());
        jobVacancies.setSpecialCatId(jobPositionsDTO.getSpecial_cat_id());

        return jobVacancies;
    }

    //save - jobVacancies
    public JobVacanciesEntity createJobVacancies(JobPositionsDTO jobPositionsDTO, UUID position_id){
        JobVacanciesEntity jobVacancies = setValues(jobPositionsDTO,position_id);
        return jobVacanciesRepository.save(jobVacancies);
    }

    public JobVacanciesEntity getByPositionIdJobVacancies(UUID position_id){
        return jobVacanciesRepository.findByPositionId(position_id);
    }

    public void updateJobVacancies(JobPositionsDTO jobPositionsDTO, UUID positionId) {
        JobVacanciesEntity existingVacancy = jobVacanciesRepository.findByPositionId(positionId);
        if (existingVacancy != null) {
            existingVacancy.setNoOfVacancies(jobPositionsDTO.getNo_of_vacancies());
            existingVacancy.setLocationId(jobPositionsDTO.getLocation_id());
            existingVacancy.setReservationCatId(jobPositionsDTO.getReservation_cat_id());
            existingVacancy.setSpecialCatId(jobPositionsDTO.getSpecial_cat_id());
            jobVacanciesRepository.save(existingVacancy);
        } else {
            throw new RuntimeException("Job vacancy not found for position ID: " + positionId);
        }
    }
}