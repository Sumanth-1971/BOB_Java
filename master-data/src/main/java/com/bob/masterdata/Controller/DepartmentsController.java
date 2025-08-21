package com.bob.masterdata.Controller;

import com.bob.db.entity.Departments;
import com.bob.masterdata.Service.DepartmentsService;
import com.bob.db.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentsController {
    @Autowired
    private DepartmentsService departmentsService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Departments>>> getAllCities(){
        try{
            List<Departments> cities=departmentsService.getAllDepartments();
            ApiResponse<List<Departments>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<Departments>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Departments>> createdepartments(@RequestBody Departments departments){

        try{
            Departments msg=departmentsService.createDepartment(departments);
            ApiResponse<Departments> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Departments> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Departments>> updatedepartments(@PathVariable Long id,@RequestBody Departments departments){
        try{
            Departments msg=departmentsService.updateDepartments(id, departments);
            ApiResponse<Departments> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Departments> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Departments>> deletedepartments(@PathVariable Long id){
        try{
            Departments departments=departmentsService.deleteDepartments(id);
            ApiResponse<Departments> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",departments);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Departments> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
