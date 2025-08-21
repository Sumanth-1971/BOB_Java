package com.bob.candidatedetails.Feign;

import com.bob.db.dto.ApiResponse;
import com.bob.db.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "positions-service",url = "http://docs.sentrifugo.com:8080/jobcreation")
public interface FeignPositionDTO {

    @GetMapping("/api/getByPositionId/{position_id}")
    ApiResponse<ResponseDTO> getById(@PathVariable("position_id") UUID positionId);
}
