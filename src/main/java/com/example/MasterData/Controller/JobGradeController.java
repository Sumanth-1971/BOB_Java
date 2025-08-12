package com.example.MasterData.Controller;

import com.example.MasterData.Model.City;
import com.example.MasterData.Model.EducationalQualifications;
import com.example.MasterData.Model.JobGrade;
import com.example.MasterData.Service.JobGradeService;
import com.example.MasterData.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobgrade")
public class JobGradeController {
    @Autowired
    private JobGradeService jobGradeService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<JobGrade>>> getAllJobGrades(){
        try{
            List<JobGrade> cities=jobGradeService.getAllJobGrades();
            ApiResponse<List<JobGrade>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<JobGrade>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<JobGrade>> createJobGrade(@RequestBody JobGrade jobGrade){

        try{
            JobGrade jobGrade1=jobGradeService.createJobGrade(jobGrade);
            ApiResponse<JobGrade> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",jobGrade1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<JobGrade> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<JobGrade>> updateJobGrade(@PathVariable Long id,@RequestBody JobGrade jobGrade){
        try{
            JobGrade msg=jobGradeService.updateJobGrade(id,jobGrade);
            ApiResponse<JobGrade> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<JobGrade> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<JobGrade>> deleteJobGrade(@PathVariable Long id){
        try{
            JobGrade jobGrade=jobGradeService.deleteJobGrade(id);
            ApiResponse<JobGrade> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",jobGrade);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<JobGrade> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
