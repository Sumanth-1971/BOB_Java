package com.bob.candidatedetails.Controllers;

import com.bob.candidatedetails.Service.TemplateService;
import com.bob.db.dto.ApiResponse;
import com.bob.db.entity.Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TemplateController {

    @Autowired
    private TemplateService templateService;
    @GetMapping("/templates")
    public ResponseEntity<ApiResponse<List<Templates>>> getTemplates() {
        try{
            List<Templates> templatesList = templateService.getAllTemplates();
            if (templatesList.isEmpty()) {
                return new ResponseEntity<>(
                        new ApiResponse<>(false, "No templates found", null),
                        HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(
                    new ApiResponse<>(true, "Templates found", templatesList),
                    HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    new ApiResponse<>(false, "Error "+e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
