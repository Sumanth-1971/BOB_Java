package com.example.MasterData.Controller;


import com.example.MasterData.Model.Location;
import com.example.MasterData.Service.LocationService;
import com.example.MasterData.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Location>>> getAllLocations(){
        try{
            List<Location> cities=locationService.getAllLocations();
            ApiResponse<List<Location>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<Location>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Location>> createLocation(@RequestBody Location location){

        try{
            Location location1=locationService.createLocation(location);
            ApiResponse<Location> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",location1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Location> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Location>> updateLocation(@PathVariable Long id,@RequestBody Location location){
        try{
            Location location1=locationService.updateLocations(id,location);
            ApiResponse<Location> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",location1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Location> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Location>> deleteLocation(@PathVariable Long id){
        try{
            Location location1=locationService.deleteLocation(id);
            ApiResponse<Location> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",location1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<Location> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
