package com.example.JobCreation.controller;

import com.example.JobCreation.repository.DashboardRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardRepository dashboardRepository;

    @GetMapping("/metrics")
    public JsonNode getMetrics() throws Exception {
        return dashboardRepository.getMetricsJson();
    }
    @GetMapping("/queries")
    public JsonNode getQueries() throws Exception {
        return dashboardRepository.getQueriesJson();
    }
}
