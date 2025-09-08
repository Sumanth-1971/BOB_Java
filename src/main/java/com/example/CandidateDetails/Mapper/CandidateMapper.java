package com.example.CandidateDetails.Mapper;

import com.example.CandidateDetails.Model.Candidates;
import com.example.CandidateDetails.dto.CandidateDetails;
import com.example.CandidateDetails.dto.CandidatesDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
//    private UUID candidate_id;
//    private String full_name;
//    private String email;
//    private String phone;
//    private LocalDate date_of_birth;
//    private String gender;
//    private String id_proof;
//    private Integer nationality_id;
//    private Integer reservation_category_id;
//    private Integer special_category_id;
//    private Integer highest_qualification_id;
//    private String total_experience;
//    private String address;
//    private String comments;
//    private String skills;
//    private String current_designation;
//    private String current_employer;
//    private String file_url;
//    private String education_qualification;
//    @JsonProperty("document_url")
//    private String documentUrl;
public class CandidateMapper {

    public static CandidatesDTO toDTO(Candidates candidate) {
        CandidatesDTO dto = new CandidatesDTO();
//        private UUID candidate_id
        dto.setCandidate_id(candidate.getCandidate_id());
        //    private String full_name;
        dto.setFull_name(candidate.getFull_name());
        //    private String email;
        dto.setEmail(candidate.getEmail());
        //    private String phone;
        dto.setPhone(candidate.getPhone());
        //    private LocalDate date_of_birth;
        dto.setDate_of_birth(candidate.getDate_of_birth());
        //    private String gender;
        dto.setGender(candidate.getGender());
        //    private String id_proof;
        dto.setId_proof( candidate.getId_proof());
        //    private Integer nationality_id;
        dto.setNationality_id(candidate.getNationality_id());
        //    private Integer reservation_category_id;
        dto.setReservation_category_id(candidate.getReservation_category_id());
        //    private Integer special_category_id;
        dto.setSpecial_category_id(candidate.getSpecial_category_id());
        //    private Integer highest_qualification_id;
        dto.setHighest_qualification_id(candidate.getHighest_qualification_id());
        //    private String total_experience;
        dto.setTotal_experience(candidate.getTotal_experience());
        //    private String address;
        dto.setAddress(candidate.getAddress());
        //    private String comments;
        dto.setComments(candidate.getComments());
        //    private String skills;
        dto.setSkills(candidate.getSkills());
        //    private String current_designation;
        dto.setCurrent_designation(candidate.getCurrent_designation());
        //    private String current_employer;
        dto.setCurrent_employer(candidate.getCurrent_employer());
        //    private String file_url;
        dto.setFile_url(candidate.getFile_url());
        //    private String education_qualification;
        dto.setEducation_qualification(candidate.getEducation_qualification());
        //    private String documentUrl;
        dto.setDocumentUrl(candidate.getDocumentUrl());
        dto.setRank(candidate.getRank());
        return dto;

    }

    public static List<CandidatesDTO> toDTOs(List<Candidates> candidate) {
        return candidate.stream().map(CandidateMapper::toDTO).toList();
    }
    public static List<Candidates> toEntities(List<CandidatesDTO> candidatesDTO) {
        return candidatesDTO.stream().map(CandidateMapper::toEntity).toList();
    }
    public static Candidates toEntity(CandidatesDTO candidatesDTO) {
        Candidates candidate = new Candidates();
        candidate.setCandidate_id(candidatesDTO.getCandidate_id());
        candidate.setFull_name(candidatesDTO.getFull_name());
        candidate.setEmail(candidatesDTO.getEmail());
        candidate.setPhone(candidatesDTO.getPhone());
        candidate.setDate_of_birth(candidatesDTO.getDate_of_birth());
        candidate.setGender(candidatesDTO.getGender());
        candidate.setId_proof(candidatesDTO.getId_proof());
        candidate.setNationality_id(candidatesDTO.getNationality_id());
        candidate.setReservation_category_id(candidatesDTO.getReservation_category_id());
        candidate.setSpecial_category_id(candidatesDTO.getSpecial_category_id());
        candidate.setHighest_qualification_id(candidatesDTO.getHighest_qualification_id());
        candidate.setTotal_experience(candidatesDTO.getTotal_experience());
        candidate.setAddress(candidatesDTO.getAddress());
        candidate.setComments(candidatesDTO.getComments());
        candidate.setSkills(candidatesDTO.getSkills());
        candidate.setCurrent_designation(candidatesDTO.getCurrent_designation());
        candidate.setCurrent_employer(candidatesDTO.getCurrent_employer());
        candidate.setFile_url(candidatesDTO.getFile_url());
        candidate.setEducation_qualification(candidatesDTO.getEducation_qualification());
        candidate.setDocumentUrl(candidatesDTO.getDocumentUrl());

        return candidate;
    }

    public static CandidateDetails toCandidateDetails(Candidates candidate) {
        CandidateDetails details = new CandidateDetails();
        details.setCandidate_id(candidate.getCandidate_id());
        details.setFull_name(candidate.getFull_name());
        details.setEmail(candidate.getEmail());
        details.setPhone(candidate.getPhone());
        details.setDate_of_birth(candidate.getDate_of_birth());
        details.setGender(candidate.getGender());
        details.setId_proof(candidate.getId_proof());
        details.setNationality_id(candidate.getNationality_id());
        details.setReservation_category_id(candidate.getReservation_category_id());
        details.setSpecial_category_id(candidate.getSpecial_category_id());
        details.setHighest_qualification_id(candidate.getHighest_qualification_id());
        details.setTotal_experience(candidate.getTotal_experience());
        details.setAddress(candidate.getAddress());
        details.setComments(candidate.getComments());
        details.setSkills(candidate.getSkills());
        details.setCurrent_designation(candidate.getCurrent_designation());
        details.setCurrent_employer(candidate.getCurrent_employer());
        details.setFile_url(candidate.getFile_url());
        details.setFileUrl(candidate.getFile_url());
        details.setEducation_qualification(candidate.getEducation_qualification());
        details.setDocumentUrl(candidate.getDocumentUrl());
        details.setRank(candidate.getRank());
        return details;
    }

    public static List<CandidateDetails> toCandidateDetailsList(List<Candidates> candidates) {
        return candidates.stream().map(CandidateMapper::toCandidateDetails).toList();
    }

}
