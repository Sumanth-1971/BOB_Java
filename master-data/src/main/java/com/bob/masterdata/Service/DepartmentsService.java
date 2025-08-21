package com.bob.masterdata.Service;

import com.bob.db.entity.Departments;
import com.bob.db.repository.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentsService {
    @Autowired
    private DepartmentsRepository departmentsRepository;

    public Departments createDepartment(Departments departments) {
        try {
            departments.setCreated_date(LocalDateTime.now());
            departmentsRepository.save(departments);
            return departments;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Departments> getAllDepartments() throws Exception {
        try {
            return departmentsRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Failed to fetch departments");
        }
    }

    public Departments updateDepartments(Long id, Departments departments) {
        try {
            Departments departments1=departments;
            Optional<Departments> existingDepartment = departmentsRepository.findById(id);
            if (existingDepartment.isPresent()) {
                departments.setDepartment_id(id);
                departmentsRepository.save(departments);
                return departments1;
            } else {
                throw new Exception("ID DOESN'T EXIST!");
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Departments deleteDepartments(Long id) {
        try {
            if (departmentsRepository.existsById(id)) {
                Departments departments=departmentsRepository.findById(id).get();
                departmentsRepository.deleteById(id);
                return departments;
            } else {
                throw new Exception("ID DOESN'T EXIST");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
