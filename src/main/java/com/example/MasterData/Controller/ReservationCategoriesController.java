package com.example.MasterData.Controller;

import com.example.MasterData.Model.Location;
import com.example.MasterData.Model.ReservationCategories;
import com.example.MasterData.Service.ReservationCategoriesService;
import com.example.MasterData.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class ReservationCategoriesController {
    @Autowired
    private ReservationCategoriesService reservationCategoriesService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ReservationCategories>>> getAllReservations(){
        try{
            List<ReservationCategories> cities=reservationCategoriesService.getAllResCategories();
            ApiResponse<List<ReservationCategories>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<ReservationCategories>> response=new ApiResponse<>(false,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ReservationCategories>> createReservation(@RequestBody ReservationCategories reservationCategories){

        try{
            ReservationCategories reservationCategories1=reservationCategoriesService.createReservationCategories(reservationCategories);
            ApiResponse<ReservationCategories> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",reservationCategories1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<ReservationCategories> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ReservationCategories>> updateReservations(@PathVariable Long id,@RequestBody ReservationCategories reservationCategories){
        try{
            ReservationCategories reservationCategories1=reservationCategoriesService.updateResCategories(id,reservationCategories);
            ApiResponse<ReservationCategories> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",reservationCategories1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<ReservationCategories> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<ReservationCategories>> deleteReservations(@PathVariable Long id){
        try{
            ReservationCategories reservationCategories=reservationCategoriesService.deleteReservationCategory(id);
            ApiResponse<ReservationCategories> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",reservationCategories);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<ReservationCategories> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
