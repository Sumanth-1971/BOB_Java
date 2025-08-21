package com.bob.db.repository;

import com.bob.db.entity.CandidateDocuments;
import com.bob.db.dto.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CandidateDocumentsRepository extends JpaRepository<CandidateDocuments, UUID> {

    @Query(value = "SELECT * FROM candidate_documents WHERE candidate_id = :candidate_id", nativeQuery = true)
    List<CandidateDocuments> findByPositionId(@Param("candidate_id") UUID candidate_id);

    @Query(value = "SELECT cd.document_id, cd.file_name, cd.file_url FROM candidate_documents cd WHERE cd.candidate_id = :candidate_id", nativeQuery = true)
    List<FileInfo> getFileDetails(@Param("candidate_id") UUID candidateId);

    @Query(value = "SELECT application_id FROM candidate_documents WHERE candidate_id = :candidateId AND application_id = :applicationId", nativeQuery = true)
    UUID findByCandidateIdAndPositionId(@Param("candidateId") UUID candidateId, @Param("applicationId") UUID applicationId);




}
