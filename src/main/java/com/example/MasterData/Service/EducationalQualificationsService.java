package com.example.MasterData.Service;

import com.example.MasterData.Model.City;
import com.example.MasterData.Model.EducationalQualifications;
import com.example.MasterData.Repository.EducationalQualificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EducationalQualificationsService {
    @Autowired
    private EducationalQualificationsRepository educationalQualificationsRepository;



    public EducationalQualifications createEduQual(EducationalQualifications educationalQualifications) {
        try {
            educationalQualifications.setCreated_date(LocalDateTime.now());
            educationalQualifications.setUpdated_date(LocalDateTime.now());
            educationalQualificationsRepository.save(educationalQualifications);
            return educationalQualifications;
        } catch (Exception e) {
            return null;
        }
    }

    public List<EducationalQualifications> getAllEduQual() throws Exception {
        try {
            return educationalQualificationsRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch Qualifications");
        }
    }

    public EducationalQualifications updateEduQual(Long id, EducationalQualifications educationalQualifications) {
        try {
            EducationalQualifications educationalQualifications1=educationalQualifications;
            Optional<EducationalQualifications> existingEdu = educationalQualificationsRepository.findById(id);
            if (existingEdu.isPresent()) {
                educationalQualifications.setEdu_qualification_id(id);
                educationalQualifications.setUpdated_date(LocalDateTime.now());
                educationalQualificationsRepository.save(educationalQualifications);
                return educationalQualifications1;

            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public EducationalQualifications deleteEduQual(Long id) {
        try {
            if (educationalQualificationsRepository.existsById(id)) {
                EducationalQualifications educationalQualifications=educationalQualificationsRepository.findById(id).get();
                educationalQualificationsRepository.deleteById(id);
                return educationalQualifications;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
