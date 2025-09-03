package com.example.JobCreation.repository;

import com.example.JobCreation.model.DepartmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DepartmentsRepository extends JpaRepository<DepartmentsEntity, Long> {

    @Query(value = "SELECT d.department_name FROM departments d", nativeQuery = true)
    List<String> getDepartmentNames();

    @Query(value = "SELECT dd.department_id,dd.department_name FROM departments dd", nativeQuery = true)
    List<Map<Long,String>> getData();
}
