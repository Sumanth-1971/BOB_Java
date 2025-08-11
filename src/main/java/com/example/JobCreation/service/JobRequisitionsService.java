package com.example.JobCreation.service;

import com.example.JobCreation.model.JobRequisitions;
import com.example.JobCreation.repository.JobRequisitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class JobRequisitionsService {

    @Autowired
    JobRequisitionsRepository jobRequisitionsRepository;

   public List<JobRequisitions>  getAll(){
        return jobRequisitionsRepository.findAll();
    }

    public List<JobRequisitions> findByRequisitionStatus(String requisitionStatus){
       return jobRequisitionsRepository.findByRequisitionStatus(requisitionStatus);
    }
    public JobRequisitions createRequisitions(JobRequisitions jobRequisitions){
       jobRequisitions.setRequisition_code("JREQ-" +(1000+ jobRequisitionsRepository.count()));
       jobRequisitions.setRequisition_id(UUID.randomUUID());
       jobRequisitions.setRequisition_status("Submitted");
        LocalDateTime tme =LocalDateTime.now();
       jobRequisitions.setCreated_date(tme);
       jobRequisitions.setUpdated_date(tme);
       return jobRequisitionsRepository.save(jobRequisitions);
    }

    public List<JobRequisitions> createBulkRequistions(List<JobRequisitions> jobRequisitionsList) {
        LocalDateTime tme = LocalDateTime.now();
        for (JobRequisitions jobRequisitions : jobRequisitionsList) {
            jobRequisitions.setRequisition_code("JREQ-" + (1000 + jobRequisitionsRepository.count()));
            jobRequisitions.setRequisition_id(UUID.randomUUID());
            jobRequisitions.setRequisition_status("Submitted");
            jobRequisitions.setCreated_date(tme);
            jobRequisitions.setUpdated_date(tme);
        }
        return jobRequisitionsRepository.saveAll(jobRequisitionsList);
    }


    public JobRequisitions updateRequisitions(JobRequisitions jobRequisitions){
        jobRequisitions.setUpdated_date(LocalDateTime.now());
        return jobRequisitionsRepository.save(jobRequisitions);
    }

    public String deleteRequisitions(UUID id) {
       try {
           if (!jobRequisitionsRepository.existsById(id)) {
               return "Requisition not found";
           }
           jobRequisitionsRepository.deleteById(id);
           return "Deleted Successful";
       }catch (Exception e){
           return "Deleted Unsuccessful"+e.getMessage() ;
       }

    }
}
