package com.bob.candidatedetails.dto;

import java.util.UUID;

public class FileInfo {

    private UUID document_id;

    private String file_name;

    private String file_url;

    public FileInfo(UUID document_id, String file_name, String file_url) {
        this.document_id = document_id;
        this.file_name = file_name;
        this.file_url = file_url;
    }

    public UUID getDocument_id() {
        return document_id;
    }

    public void setDocument_id(UUID document_id) {
        this.document_id = document_id;
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
}
