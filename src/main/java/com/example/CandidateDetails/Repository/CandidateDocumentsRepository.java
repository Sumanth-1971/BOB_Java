package com.example.CandidateDetails.Repository;

import com.example.CandidateDetails.Model.CandidateApplications;
import com.example.CandidateDetails.Model.Candidate_documents;
import com.example.CandidateDetails.dto.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
@Repository
public interface CandidateDocumentsRepository extends JpaRepository<Candidate_documents, UUID> {

    @Query(value = "SELECT * FROM candidate_documents WHERE candidate_id = :candidate_id", nativeQuery = true)
    List<Candidate_documents> findByPositionId(@Param("candidate_id") UUID candidate_id);

    @Query(value = "SELECT cd.document_id, cd.file_name, cd.file_url FROM candidate_documents cd WHERE cd.candidate_id = :candidate_id", nativeQuery = true)
    List<FileInfo> getFileDetails(@Param("candidate_id") UUID candidateId);



}
