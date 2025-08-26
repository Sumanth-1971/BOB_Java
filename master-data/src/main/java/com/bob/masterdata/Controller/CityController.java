package com.bob.masterdata.Controller;

import com.bob.db.entity.CityEntity;
import com.bob.masterdata.Service.CityService;
import com.bob.db.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    private CityService cityService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CityEntity>>> getAllCities(){
        try{
            List<CityEntity> cities=cityService.getAllCities();
            ApiResponse<List<CityEntity>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<CityEntity>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CityEntity>> createCity(@RequestBody CityEntity city){

        try{
            CityEntity msg=cityService.createCity(city);
            ApiResponse<CityEntity> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<CityEntity> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CityEntity>> updateCity(@PathVariable Long id, @RequestBody CityEntity city){
        try{
            CityEntity msg=cityService.updateCities(id, city);
            ApiResponse<CityEntity> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<CityEntity> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<CityEntity>> deleteCity(@PathVariable Long id){
        try{
            CityEntity city=cityService.deleteCities(id);
            ApiResponse<CityEntity> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",city);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<CityEntity> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
