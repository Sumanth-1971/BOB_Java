package com.example.CandidateDetails.Service;

import com.example.CandidateDetails.Model.*;
import com.example.CandidateDetails.Repository.*;
import com.example.CandidateDetails.dto.CandidateDetails;
import com.example.CandidateDetails.dto.FileInfo;
import com.example.CandidateDetails.dto.Interviewdto;
import com.example.CandidateDetails.dto.Offerdto;
import io.swagger.v3.oas.models.media.EmailSchema;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateNationalityRepository candidateNationalityRepository;

    @Autowired
    private CandidateApplicationsRepository candidateApplicationsRepository;
    @Autowired
    private MailService mailService;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CandidateDocumentsRepository candidateDocumentsRepository;
    @Autowired
    private JobPostingLocationRepository jobPostingLocationRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private InterviewerRepository interviewerRepository;
    public List<CandidateDetails> getCandidateDetailsByPositionId(UUID position_id){
        List<CandidateApplications> candidateApplicationsList=candidateApplicationsRepository.findByPositionId(position_id);
        List<CandidateDetails> candidateDetailsList=new ArrayList<>();
        for(CandidateApplications candidateApplications:candidateApplicationsList){
            CandidateDetails candidateDetails=new CandidateDetails();
            List<FileInfo> fileInfoList = candidateDocumentsRepository.getFileDetails(candidateApplications.getCandidate_id());
//            locationRepository.findById(jobPostingLocationRepository.findByPositionId(position_id).get(0).);
            System.out.println("File Info List: " + fileInfoList);
            candidateDetails.setCandidate_id(candidateApplications.getCandidate_id());
            candidateDetails.setFull_name(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getFull_name());
            candidateDetails.setUsername(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getUsername());
            candidateDetails.setEmail(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getEmail());
            candidateDetails.setPhone(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getPhone());
            candidateDetails.setReservation_category_id(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getReservation_category_id());
            candidateDetails.setHighest_qualification(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getHighest_qualification_id());
            candidateDetails.setTotal_experience(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getTotal_experience());
            candidateDetails.setAddress(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getAddress());
            candidateDetails.setGender(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getGender());
            candidateDetails.setSpecial_category_id(candidateRepository.findById(candidateApplications.getCandidate_id()).get().getSpecial_category_id());
            candidateDetails.setFileInfo(fileInfoList);
            candidateDetails.setApplication_status(candidateApplications.getApplication_status());
//            candidateDetails.setState_details(jobPostingLocationRepository.);
//            candidateDetails.setCountry_id();
            candidateDetailsList.add(candidateDetails);

        }
        return candidateDetailsList;
    }

    public List<Candidates> getDetailsByPositionId(UUID position_id) {
        List<CandidateApplications> candidateApplicationsList= candidateApplicationsRepository.findByPositionId(position_id);
        if (candidateApplicationsList.isEmpty()) {
            return null; // or throw an exception if no applications found
        }
        List<Candidates> candidatesList = new ArrayList<>();
        for(CandidateApplications candidateApplications : candidateApplicationsList) {
            Candidates candidates = candidateRepository.findById(candidateApplications.getCandidate_id()).orElse(null);
            if (candidates != null) {
                candidatesList.add(candidates);
            }
        }
        return candidatesList;
    }

    public Candidates getCandidatesById(UUID candidate_id) {
        return candidateRepository.findById(candidate_id).orElse(null);
    }



    @Transactional
    public String scheduleInterview(Interviewdto interviewdto) {
        // 1. Validate incoming DTO data early
        if (interviewdto.getCandidate_id() == null || interviewdto.getUserId() == null || interviewdto.getPosition_id() == null) {
            return "Invalid input data: Candidate, User, or Position ID is missing.";
        }

        UUID candidateId = interviewdto.getCandidate_id();
        UUID positionId = interviewdto.getPosition_id();
        Integer userId = interviewdto.getUserId();

        // 2. Fetch all necessary entities at the beginning with proper null checks
        Candidates candidate = candidateRepository.findById(candidateId)
                .orElse(null);
        if (candidate == null) {
            return "Candidate not found!";
        }

        Users interviewer = usersRepository.findById(userId)
                .orElse(null);
        if (interviewer == null) {
            return "Interviewer not found!";
        }

        String positionTitle = positionRepository.findById(positionId)
                .map(Position::getPosition_title)
                .orElse("Unknown Position");

        // 3. Prepare data for emails
        String interviewerName = interviewer.getName();
        String interviewerEmail = interviewer.getEmail();
        String candidateName = candidate.getFull_name();
        String candidateEmail = candidate.getEmail();

        Map<String, Object> emailData = new HashMap<>();
        emailData.put("INTERVIEWER_NAME", interviewerName);
        emailData.put("CANDIDATE_NAME", candidateName);
        emailData.put("POSITION_TITLE", positionTitle);
        emailData.put("DATE", interviewdto.getDate().toString());
        emailData.put("TIME", interviewdto.getInterview_time().toString());

        // 4. Send emails
        boolean candidateMailSent = sendEmailWithRetryMechanism(
                candidateEmail,
                "Interview Scheduled for " + positionTitle,
                "Candidate",
                emailData
        );

        boolean interviewerMailSent = sendEmailWithRetryMechanism(
                interviewerEmail,
                "Interview Scheduled: " + candidateName + " for " + positionTitle,
                "Interviewer",
                emailData
        );

        // 5. Update database only if emails are sent
        if (candidateMailSent && interviewerMailSent) {
            CandidateApplications candidateApplications = candidateApplicationsRepository
                    .findByCandidateIdAndPositionId(candidateId, positionId).get();

            if (candidateApplications != null) {
                candidateApplications.setUpdated_date(LocalDateTime.now());
                candidateApplications.setApplication_status("Scheduled!");
                candidateApplicationsRepository.save(candidateApplications);
            } else {

                return "Warning: Candidate application not found for status update.";
            }

            Interviews interview = new Interviews();
            interview.setCandidate_id(candidateId);
            interview.setPosition_id(positionId);
            interview.setType("Online");
            if (interviewdto.getDate() == null || interviewdto.getInterview_time() == null) {
                throw new IllegalArgumentException("Date or Interview Time is missing!");
            }
            LocalDateTime scheduleTime = LocalDateTime.of(
                    interviewdto.getDate(),
                    interviewdto.getInterview_time()
            );
            interview.setSchedule_at(scheduleTime);
            interview.setInterviewer(interviewerName);
            interview.setStatus("Scheduled");
            interviewerRepository.save(interview);

            return "Interview Scheduled!";
        }
        return "Failed to send one or both interview scheduling emails!";
    }

    private boolean sendEmailWithRetryMechanism(String toEmail, String subject, String template, Map<String, Object> variables) {
        for (int i = 0; i < 3; i++) {
            String res = mailService.sendSimpleEmail(toEmail, subject, template, variables);
            if (res.equals("Mail Sent!")) {
                return true;
            }
        }
        return false;
    }

    public String offer(Offerdto offerdto) throws MessagingException, UnsupportedEncodingException {

        try {
//            CandidateApplications candidateApplications = candidateApplicationsRepository.findById(application_id).orElse(null);
//            if (candidateApplications == null) {
//                return "No Candidate present!";
//            }
            Candidates candidates=candidateRepository.findById(offerdto.getCandidate_id()).orElse(null);
            Map<String, Object> offerDetails = new HashMap<>();
            offerDetails.put("COMPANY_NAME", "BANK OF BARODA!");
            offerDetails.put("CANDIDATE_NAME", candidates.getFull_name());
            offerDetails.put("POSITION_TITLE", offerdto.getPosition_id());


            String email = candidates.getEmail();
//            String email="sumanthsangam2@gmail.com";
            String path = offerdto.getOffer_letter_path();

//            String path="C:\\Users\\sumanth.sangam\\Downloads\\Academic_CV_Template.pdf";
            boolean candidateOfferLetterSSent = sendEmailWithAttachmentRetry(email, "Offer Letter!", "OfferLetter", offerDetails, path);

            if (candidateOfferLetterSSent) {
//                candidateApplications.setApplication_status("Offered");
                CandidateApplications candidateApplications= candidateApplicationsRepository.findByCandidateIdAndPositionId(offerdto.getCandidate_id(), offerdto.getPosition_id()).orElse(null);
                candidateApplications.setUpdated_date(LocalDateTime.now());
                candidateApplications.setApplication_status("Offered");
                candidateApplicationsRepository.save(candidateApplications);

                return "Offer sent!";
            }

            return "Failed to send offer letter mail";
        }catch (Exception e){
            return "Failed to send email due to"+e.getMessage();
        }
    }


    public boolean sendEmailWithAttachmentRetry(String toEmail, String subject, String template, Map<String, Object> variables,String attachment) throws MessagingException, UnsupportedEncodingException {
        for (int i = 0; i < 3; i++) {
            String res = mailService.sendEmailWithAttachment(toEmail,subject,attachment,variables,template);
            if (res.equals("Mail Sent with attachment!")) {
                return true;
            }
        }
        return false;
    }
}
