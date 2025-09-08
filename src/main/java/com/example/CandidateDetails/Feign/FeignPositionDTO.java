package com.example.CandidateDetails.Feign;

import com.example.CandidateDetails.dto.ApiResponse;
import com.example.CandidateDetails.dto.JobPositionsDTO;
import com.example.CandidateDetails.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "positions-service",url = "https://bobjava.sentrifugo.com:8443")
public interface FeignPositionDTO {

    @GetMapping("/jobcreation/api/getByPositionId/{position_id}")
    ApiResponse<ResponseDTO> getById(@PathVariable("position_id") UUID positionId);

    @GetMapping("/jobcreation/api/active_jobs")
    public ResponseEntity<ApiResponse<List<JobPositionsDTO>>> getActiveJobs() ;
}
