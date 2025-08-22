package com.bob.candidatedetails.Service;

import com.bob.db.entity.Templates;
import com.bob.db.repository.TemplateRepository;
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
