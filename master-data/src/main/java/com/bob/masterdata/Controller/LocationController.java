package com.bob.masterdata.Controller;


import com.bob.db.entity.LocationEntity;
import com.bob.masterdata.Service.LocationService;
import com.bob.db.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<LocationEntity>>> getAllLocations(){
        try{
            List<LocationEntity> cities=locationService.getAllLocations();
            ApiResponse<List<LocationEntity>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<LocationEntity>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<LocationEntity>> createLocation(@RequestBody LocationEntity location){

        try{
            LocationEntity location1=locationService.createLocation(location);
            ApiResponse<LocationEntity> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",location1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<LocationEntity> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<LocationEntity>> updateLocation(@PathVariable Long id, @RequestBody LocationEntity location){
        try{
            LocationEntity location1=locationService.updateLocations(id,location);
            ApiResponse<LocationEntity> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",location1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<LocationEntity> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<LocationEntity>> deleteLocation(@PathVariable Long id){
        try{
            LocationEntity location1=locationService.deleteLocation(id);
            ApiResponse<LocationEntity> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",location1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<LocationEntity> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
