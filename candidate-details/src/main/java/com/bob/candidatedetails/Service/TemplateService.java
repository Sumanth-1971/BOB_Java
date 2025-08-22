package com.example.CandidateDetails.Service;

import com.example.CandidateDetails.Model.Templates;
import com.example.CandidateDetails.Repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    public List<Templates> getAllTemplates() {
        return templateRepository.findAll();
    }
}
