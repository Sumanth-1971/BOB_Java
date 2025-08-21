package com.bob.masterdata.Controller;

import com.bob.db.entity.State;
import com.bob.masterdata.Service.StateService;
import com.bob.db.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/state")
public class StateController {
    @Autowired
    private StateService stateService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<State>>> getAllStates(){
        try{
            List<State> cities=stateService.getAllStates();
            ApiResponse<List<State>> response=new ApiResponse<>(true,"DATA FIELDS FETCHED SUCCESSFULLY!",cities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<List<State>> response=new ApiResponse<>(true,"DATA FIELDS NOT FETCHED!",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<State>> createState(@RequestBody State state){

        try{
            State state1=stateService.createState(state);
            ApiResponse<State> response=new ApiResponse<>(true,"DATA FIELDS CREATED SUCCESSFULLY!",state1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<State> response=new ApiResponse<>(false,"DATA FIELDS NOT CREATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<State>> updateState(@PathVariable Long id,@RequestBody State state){
        try{
            State state1=stateService.updateState(id, state);
            ApiResponse<State> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",state1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<State> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED!"+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<State>> deleteState(@PathVariable Long id){
        try{
            State state=stateService.deleteState(id);
            ApiResponse<State> response=new ApiResponse<>(true,"DATA FIELD UPDATED SUCCESSFULLY!",state);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<State> response=new ApiResponse<>(false,"DATA FIELDS NOT UPDATED! "+e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
