package com.bob.masterdata.Controller;

import com.bob.db.entity.EducationalQualifications;
import com.bob.masterdata.Service.EducationalQualificationsService;
import com.bob.db.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/eduQual")
public class EducationalQualificationsController {

    @Autowired
    private EducationalQualificationsService educationalQualificationsService;
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<EducationalQualifications>>> getAllEduQual(){
        try{
            List<EducationalQualifications> cities=educationalQualificationsService.getAllEduQual();
            ApiResponse<List<EducationalQualifications>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<EducationalQualifications>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<EducationalQualifications>> createEduQual(@RequestBody EducationalQualifications educationalQualifications){

        try{
            EducationalQualifications educationalQualifications1=educationalQualificationsService.createEduQual(educationalQualifications);
            ApiResponse<EducationalQualifications> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",educationalQualifications1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<EducationalQualifications> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<EducationalQualifications>> updateEduQual(@PathVariable Long id,@RequestBody EducationalQualifications educationalQualifications){
        try{
            EducationalQualifications msg=educationalQualificationsService.updateEduQual(id,educationalQualifications);
            ApiResponse<EducationalQualifications> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<EducationalQualifications> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<EducationalQualifications>> deleteEduQual(@PathVariable Long id){
        try{
            EducationalQualifications educationalQualifications=educationalQualificationsService.deleteEduQual(id);
            ApiResponse<EducationalQualifications> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",educationalQualifications);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<EducationalQualifications> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
