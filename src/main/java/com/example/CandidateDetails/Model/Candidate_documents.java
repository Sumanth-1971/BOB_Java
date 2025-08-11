package com.example.CandidateDetails.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Candidate_documents {

    @Id
    private UUID document_id;

    private  UUID candidate_id;

    private String document_type;

    private UUID application_id;


    private String file_name;

    private String file_url;

    private LocalDateTime uploaded_date;

    public Candidate_documents() {
    }

    public Candidate_documents(UUID document_id, UUID candidate_id, String document_type, UUID application_id, String file_name, String file_url, LocalDateTime uploaded_date) {
        this.document_id = document_id;
        this.candidate_id = candidate_id;
        this.document_type = document_type;
        this.application_id = application_id;
        this.file_name = file_name;
        this.file_url = file_url;
        this.uploaded_date = uploaded_date;
    }

    public UUID getDocument_id() {
        return document_id;
    }

    public void setDocument_id(UUID document_id) {
        this.document_id = document_id;
    }

    public UUID getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(UUID candidate_id) {
        this.candidate_id = candidate_id;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public UUID getApplication_id() {
        return application_id;
    }

    public void setApplication_id(UUID application_id) {
        this.application_id = application_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public LocalDateTime getUploaded_date() {
        return uploaded_date;
    }

    public void setUploaded_date(LocalDateTime uploaded_date) {
        this.uploaded_date = uploaded_date;
    }
}
