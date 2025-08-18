package com.example.CandidateDetails.Feign;

import com.example.CandidateDetails.dto.ApiResponse;
import com.example.CandidateDetails.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "positions-service",url = "http://docs.sentrifugo.com:8080/jobcreation")
public interface FeignPositionDTO {

    @GetMapping("/api/getByPositionId/{position_id}")
    ApiResponse<ResponseDTO> getById(@PathVariable("position_id") UUID positionId);
}
