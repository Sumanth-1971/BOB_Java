package com.bob.JobCreation.service;

import com.bob.JobCreation.dto.JobPositionsDTO;
import com.bob.JobCreation.model.JobVacancies;
import com.bob.JobCreation.repository.JobVacanciesRepository;
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

    private JobVacancies setValues(JobPositionsDTO jobPositionsDTO,UUID position_id){
        JobVacancies jobVacancies = new JobVacancies();
        jobVacancies.setPosition_id(position_id);

        jobVacancies.setNo_of_vacancies(jobPositionsDTO.getNo_of_vacancies());
        jobVacancies.setLocation_id(jobPositionsDTO.getLocation_id());
        jobVacancies.setReservation_cat_id(jobPositionsDTO.getReservation_cat_id());
        jobVacancies.setSpecial_cat_id(jobPositionsDTO.getSpecial_cat_id());

        return jobVacancies;
    }

    //save - jobVacancies
    public JobVacancies createJobVacancies(JobPositionsDTO jobPositionsDTO,UUID position_id){
        JobVacancies jobVacancies = setValues(jobPositionsDTO,position_id);
        return jobVacanciesRepository.save(jobVacancies);
    }

    public JobVacancies getByPositionIdJobVacancies(UUID position_id){
        return jobVacanciesRepository.findByPositionId(position_id);
    }

    public void updateJobVacancies(JobPositionsDTO jobPositionsDTO, UUID positionId) {
        JobVacancies existingVacancy = jobVacanciesRepository.findByPositionId(positionId);
        if (existingVacancy != null) {
            existingVacancy.setNo_of_vacancies(jobPositionsDTO.getNo_of_vacancies());
            existingVacancy.setLocation_id(jobPositionsDTO.getLocation_id());
            existingVacancy.setReservation_cat_id(jobPositionsDTO.getReservation_cat_id());
            existingVacancy.setSpecial_cat_id(jobPositionsDTO.getSpecial_cat_id());
            jobVacanciesRepository.save(existingVacancy);
        } else {
            throw new RuntimeException("Job vacancy not found for position ID: " + positionId);
        }
    }
}