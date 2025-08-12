package com.example.MasterData.Controller;

import com.example.MasterData.Model.City;
import com.example.MasterData.Service.CityService;
import com.example.MasterData.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<City>>> getAllCities(){
        try{
            List<City> cities=cityService.getAllCities();
            ApiResponse<List<City>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<City>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<City>> createCity(@RequestBody City city){

        try{
            City msg=cityService.createCity(city);
            ApiResponse<City> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<City> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<City>> updateCity(@PathVariable Long id,@RequestBody City city){
        try{
            City msg=cityService.updateCities(id, city);
            ApiResponse<City> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<City> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<City>> deleteCity(@PathVariable Long id){
        try{
            City city=cityService.deleteCities(id);
            ApiResponse<City> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",city);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<City> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
