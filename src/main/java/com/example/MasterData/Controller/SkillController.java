package com.example.MasterData.Controller;

import com.example.MasterData.Model.Departments;
import com.example.MasterData.Model.ReservationCategories;
import com.example.MasterData.Model.Skill;
import com.example.MasterData.Service.SkillService;
import com.example.MasterData.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Skill>>> getAllSkills(){
        try{
            List<Skill> cities=skillService.getAllSkills();
            ApiResponse<List<Skill>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<Skill>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Skill>> createSkill(@RequestBody Skill skill){

        try{
            Skill msg=skillService.createSkill(skill);
            ApiResponse<Skill> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Skill> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Skill>> updateSkill(@PathVariable Long id,@RequestBody Skill skill){
        try{
            Skill msg=skillService.updateSKill(id,skill);
            ApiResponse<Skill> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Skill> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Skill>> deleteSkill(@PathVariable Long id){
        try{
            Skill skill=skillService.deleteSkill(id);
            ApiResponse<Skill> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",skill);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Skill> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
