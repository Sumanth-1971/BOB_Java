package com.example.CandidateDetails.Controllers;

import com.example.CandidateDetails.Model.Candidates;
import com.example.CandidateDetails.Model.Interviews;
import com.example.CandidateDetails.Service.CandidateService;
import com.example.CandidateDetails.Service.MailService;
import com.example.CandidateDetails.dto.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {
    @Autowired
    private CandidateService candidateService;


    @Autowired
    private MailService mailService;

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
    public ResponseEntity<ApiResponse<List<CandidateDetails>>> getCandidateDetailsByPositionId(@PathVariable UUID position_id) {
        try {
            List<CandidateDetails> candidateDetailsList = candidateService.getCandidateDetailsByPositionId(position_id);
            if (candidateDetailsList.isEmpty()) {
                throw new Exception("No candidates found for the given position ID.");
            }
            ApiResponse<List<CandidateDetails>> response = new ApiResponse<>(true, "Candidate details retrieved successfully", candidateDetailsList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<CandidateDetails>> response = new ApiResponse<>(false, "Couldn't retrieve Candidate details due to: " + e.getMessage(), null);
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
}
