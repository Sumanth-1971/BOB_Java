package com.bob.JobCreation.controller;

import com.bob.JobCreation.dto.ApiResponse;
import com.bob.JobCreation.dto.JobPositionsDTO;
import com.bob.JobCreation.dto.ResponseDTO;
import com.bob.JobCreation.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create_bulk_positions")
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

    @GetMapping("/active_jobs")
    public ResponseEntity<?> getActiveJobs() {
        List<JobPositionsDTO> activeJobs = jobPositionsService.getActiveJobs();

        if (activeJobs.isEmpty()) {
            ApiResponse<List<JobPositionsDTO>> apiResponse = new ApiResponse<>(
                    false,
                    "No active jobs found",
                    activeJobs
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        ApiResponse<List<JobPositionsDTO>> apiResponse = new ApiResponse<>(
                true,
                "Active jobs found",
                activeJobs
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getbyjobtitleandlocation/{job_title}/{location}")
    public ResponseEntity<?> getByJobTitleAndLocation(@PathVariable String job_title, @PathVariable Long location) {
        try {
            List<JobPositionsDTO> jobPositions = jobPositionsService.findByJobTitleAndLocation(job_title, location);
            if (jobPositions != null && !jobPositions.isEmpty()) {
                ApiResponse<List<JobPositionsDTO>> apiResponse = new ApiResponse<>(true, "Positions found for the given job title and location", jobPositions);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                ApiResponse<String> apiResponse = new ApiResponse<>(false, "No positions found for the given job title and location", null);
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<String> apiResponse = new ApiResponse<>(false, "Error retrieving positions: " + e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}