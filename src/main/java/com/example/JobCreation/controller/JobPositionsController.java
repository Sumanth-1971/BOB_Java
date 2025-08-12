package com.example.JobCreation.controller;

import com.example.JobCreation.dto.ApiResponse;
import com.example.JobCreation.dto.JobPositionsDTO;
import com.example.JobCreation.dto.ResponseDTO;
import com.example.JobCreation.model.Positions;
import com.example.JobCreation.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class JobPositionsController {

    @Autowired
    private JobPositionService jobPositionsService;


    @PostMapping("/create_positions")
    public ResponseEntity<?> createPositions(@RequestBody JobPositionsDTO positions){
        try{
            System.out.println(positions.getSelection_procedure());
            JobPositionsDTO createdPosition = jobPositionsService.createPosition(positions);
            ApiResponse<JobPositionsDTO> apiResponse = new ApiResponse<>(true, "Position created successfully", createdPosition);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        }catch (Exception e){
            ApiResponse<String> apiResponse = new ApiResponse<>(false, "Failed to create position: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update_positions")
    public ResponseEntity<?> updatePosition( @RequestBody JobPositionsDTO positions){
        try{
            JobPositionsDTO updatedPosition = jobPositionsService.updateJobposition(positions);
            ApiResponse<JobPositionsDTO> apiResponse = new ApiResponse<>(true, "Position updated successfully", updatedPosition);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (Exception e){
            ApiResponse<String> apiResponse = new ApiResponse<>(false, "Failed to update position: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getpos")
    public List<JobPositionsDTO> getAllPosition(){

        return jobPositionsService.findAllPositions();
    }
    @PostMapping("/create_Bulk_positions")
    public ResponseEntity<?> createBulkPositions(@RequestBody List<JobPositionsDTO> positionsList) {
        try {
            List<JobPositionsDTO> createdPositions = jobPositionsService.createBulkPostions(positionsList);
            ApiResponse<List<JobPositionsDTO>> apiResponse = new ApiResponse<>(true, "Positions created successfully", createdPositions);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse<String> apiResponse = new ApiResponse<>(false, "Failed to create positions: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getbyreq/{requisition_id}")
    public ResponseEntity<?>  getByreqId(@PathVariable UUID requisition_id){
        List<JobPositionsDTO> jobPositionsDTOList = jobPositionsService.findByReqId(requisition_id);
        if (jobPositionsDTOList != null && !jobPositionsDTOList.isEmpty()) {
            ApiResponse<List<JobPositionsDTO>> apiResponse = new ApiResponse<>(true, "Positions found for the given requisition ID", jobPositionsDTOList);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            ApiResponse<String> apiResponse = new ApiResponse<>(false, "No positions found for the given requisition ID", null);
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByPositionId/{position_id}")
    public ResponseEntity<?> getById(@PathVariable UUID position_id){
        try {
            ResponseDTO position = jobPositionsService.findByPositionId(position_id);
            if (position != null) {
                ApiResponse<ResponseDTO> apiResponse = new ApiResponse<>(true, "Position found", position);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse<String> apiResponse = new ApiResponse<>(false, "Position not found", null);
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<String> apiResponse = new ApiResponse<>(false, "Error retrieving position: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete_position/{position_id}")
    public ResponseEntity<?> deletePosition(@PathVariable UUID position_id) {
        try {
            String result = jobPositionsService.delectByPositionId(position_id);
            ApiResponse<String> apiResponse = new ApiResponse<>(true, "Position deleted successfully", result);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<String> apiResponse = new ApiResponse<>(false, "Failed to delete position: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
