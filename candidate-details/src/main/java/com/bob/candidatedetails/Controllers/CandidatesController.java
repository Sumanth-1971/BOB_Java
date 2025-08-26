package com.bob.candidatedetails.Controllers;

import com.bob.candidatedetails.model.GetCandidateDetailsByPositionIdResponse;
import com.bob.db.entity.Candidates;
import com.bob.db.entity.Interviews;
import com.bob.candidatedetails.Service.CalendarService;
import com.bob.candidatedetails.Service.CandidateService;
import com.bob.candidatedetails.Service.MailService;
import com.bob.db.dto.ApiResponse;
import com.bob.db.dto.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {
    @Autowired
    private CandidateService candidateService;


    @Autowired
    private MailService mailService;

    @Autowired
    private CalendarService calendarService;


    @GetMapping("/details/{position_id}")
    public ResponseEntity<ApiResponse<List<Candidates>>> getDetailsByPositionId(@PathVariable UUID position_id) {
        try{
            List<Candidates> candidateDetailsList = candidateService.getDetailsByPositionId(position_id);
            if (candidateDetailsList.isEmpty()) {
                throw new Exception("No candidates found for the given position ID.");
            }
            ApiResponse<List<Candidates>> response = new ApiResponse<>(true,"Candidate details retrieved successfully", candidateDetailsList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<Candidates>> response = new ApiResponse<>(false, "Couldn't retrieve Candidate details due to:"+e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @GetMapping("/details-by-position/{position_id}")
    public ResponseEntity<ApiResponse<List<GetCandidateDetailsByPositionIdResponse>>> getCandidateDetailsByPositionId(@PathVariable UUID position_id) {
        try {
            List<GetCandidateDetailsByPositionIdResponse> candidateDetailsList = candidateService.getCandidateDetailsByPositionId(position_id);
            if (candidateDetailsList.isEmpty()) {
                throw new Exception("No candidates found for the given position ID.");
            }
            ApiResponse<List<GetCandidateDetailsByPositionIdResponse>> response = new ApiResponse<>(true, "Candidate details retrieved successfully", candidateDetailsList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<GetCandidateDetailsByPositionIdResponse>> response = new ApiResponse<>(false, "Couldn't retrieve Candidate details due to: " + e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
    @GetMapping("/details-by-candidates/{candidate_id}")
    public ResponseEntity<ApiResponse<Candidates>> getCandidateDetailsById(@PathVariable UUID candidate_id) {
        try {
            Candidates candidate = candidateService.getCandidatesById(candidate_id);
            if (candidate == null) {
                throw new Exception("No candidate found for the given ID.");
            }
            ApiResponse<Candidates> response = new ApiResponse<>(true, "Candidate details retrieved successfully", candidate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Candidates> response = new ApiResponse<>(false, "Couldn't retrieve Candidate details due to: " + e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/schedule-interview")
    public ResponseEntity<String> scheduleInterview(@RequestBody Interviewdto interviewdto){
        return new ResponseEntity<>(candidateService.scheduleInterview(interviewdto),HttpStatus.OK);
    }

    @PutMapping("/offer")
    public String offer(@RequestBody Offerdto offerdto) throws MessagingException, UnsupportedEncodingException {
        return candidateService.offer(offerdto);
    }

    @GetMapping("/get-candidates/{status}")
    public List<CandidateDetails> getCandidateByStatus(@PathVariable String status) {
        return candidateService.getCandidateByApplicationStatus(status);
    }

    @GetMapping("/get-candidates/{status}/count")
    public Integer getCountByStatus(@PathVariable String status) {
        return candidateService.countCandidatesByStatus(status);
    }

    @GetMapping("/all")
    public List<CandidateDetails> getAllCandidates(){
        return candidateService.getAllCandidateDetails();
    }


    @PostMapping("/interviews")
    public Interviews getInterviewDetailsByCandidateId(@RequestBody InfoDto infoDto){
        return candidateService.getInterviewsByCandidateAndPositionId(infoDto.getCandidate_id(),infoDto.getPosition_id());
    }

//    @GetMapping("/interview/details/{candidate_id}/{position_id}")
//    public Interviews getInterviewDetailsByCandidateIdAndPositionId(@PathVariable UUID candidate_id, @PathVariable UUID position_id) {
//        return candidateService.getInterviewDetailsByPositionAndCandidateId(candidate_id, position_id);
//    }
//
    @PutMapping("/update-interview-status")
    public ResponseEntity<String> updateInterviewStatus(@RequestBody InterviewDetails interviewDetails) {
        try {
            candidateService.getStatus(interviewDetails);
            return new ResponseEntity<>("Interview status updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating interview status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/apply/job")
    public ResponseEntity<String> applyInterview(@RequestBody ApplyInterviewdto applyInterviewdto){
        try{
            String str=candidateService.applyInterview(applyInterviewdto.getCandidate_id(),applyInterviewdto.getPosition_id());
            if(str.equals("Applied for Job!")){
                return new ResponseEntity<>(str,HttpStatus.OK);
            }
            else{
                throw new Exception(str);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Couldn't add the data due to"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Adding data to candidate table
    @PutMapping("/update_candidate")
    public ResponseEntity<ApiResponse<?>> createCandidate(@RequestBody CandidatesDto candidate) {
        try {
            CandidatesDto createdCandidate = candidateService.updateCandidate(candidate);
            ApiResponse<?> response = new ApiResponse<>(true, "Candidate created successfully", createdCandidate);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<?> response = new ApiResponse<>(false, "Couldn't create candidate due to: " + e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("getapplied_postions/{candidate_id}")
    public ResponseEntity<List<ResponseDTO>> getDetailsByCandidateId(@PathVariable UUID candidate_id){

        try{
            List<ResponseDTO> positionDTOList=candidateService.getAllDetailsByCandidateId(candidate_id);
            return new ResponseEntity<>(positionDTOList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/interviews/by-date-range")
    public ResponseEntity<List<InterviewResponse>> getInterviewsByDateRange(
            @RequestParam long startTimestamp,
            @RequestParam long endTimestamp) {
        try {
            List<InterviewResponse> interviewsResponseList = calendarService.getInterviewSchedulesBetween(startTimestamp, endTimestamp);
            if (interviewsResponseList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(interviewsResponseList, HttpStatus.OK);
        }catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Error-Message", e.getMessage())
                    .body(Collections.emptyList());
        }
    }
}