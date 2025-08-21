package com.bob.masterdata.Service;

import com.bob.db.entity.JobGrade;
import com.bob.db.repository.JobGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobGradeService {

    @Autowired
    private JobGradeRepository jobGradeRepository;


    public JobGrade createJobGrade(JobGrade jobGrade) {
        try {
            jobGrade.setCreated_date(LocalDateTime.now());
            jobGradeRepository.save(jobGrade);
            return jobGrade;
        } catch (Exception e) {
            return null;
        }
    }

    public List<JobGrade> getAllJobGrades() throws Exception {
        try {
            return jobGradeRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch JobGrades");
        }
    }

    public JobGrade updateJobGrade(Long id, JobGrade jobGrade) {
        try {
            JobGrade jobGrade1=jobGrade;
            Optional<JobGrade> existingJobGrade = jobGradeRepository.findById(id);
            if (existingJobGrade.isPresent()) {
                jobGrade.setJob_grade_id(id);
                jobGradeRepository.save(jobGrade);
                return jobGrade1;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public JobGrade deleteJobGrade(Long id) {
        try {
            if (jobGradeRepository.existsById(id)) {
                JobGrade jobGrade=jobGradeRepository.findById(id).get();
                jobGradeRepository.deleteById(id);
                return jobGrade;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
