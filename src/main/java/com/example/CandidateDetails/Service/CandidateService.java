package com.example.CandidateDetails.Service;

import com.example.CandidateDetails.CandidateDetailsApplication;
import com.example.CandidateDetails.Feign.FeignPositionDTO;
import com.example.CandidateDetails.Mapper.CandidateMapper;
import com.example.CandidateDetails.Model.*;
import com.example.CandidateDetails.Repository.*;
import com.example.CandidateDetails.dto.*;
import com.example.CandidateDetails.entity.WorkflowApprovalEntity;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private InterviewsRepository interviewerRepository;
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    WorkflowApprovalEntityRepository workflowApprovalEntityRepository;
    @Autowired
    private FeignPositionDTO feignPositionDTO;

    public List<CandidateDetails> getCandidateDetailsByPositionId(UUID position_id) {
        List<CandidateApplications> candidateApplicationsList = candidateApplicationsRepository.findByPositionId(position_id);
        List<CandidateDetails> candidateDetailsList = new ArrayList<>();

        for (CandidateApplications candidateApplications : candidateApplicationsList) {

//            List<FileInfo> fileInfoList = candidateDocumentsRepository.getFileDetails(candidateApplications.getCandidate_id());
            String fileUrl=candidateRepository.findById(candidateApplications.getCandidate_id()).get().getFile_url();
            List<Long> locationIds= jobPostingLocationRepository.findByPositionId(position_id);
            Location location=locationRepository.findById(locationIds.get(0)).orElse(null);
            City city= cityRepository.findById(location.getCity_id()).orElse(null);
            State state = stateRepository.findById(city.getState_id()).orElse(null);
            Country country = countryRepository.findById(state.getCountry_id()).orElse(null);
            UUID candidateId=candidateApplications.getCandidate_id();
            Candidates candidates=candidateRepository.findById(candidateApplications.getCandidate_id()).get();

            CandidateDetails candidateDetails = CandidateMapper.toCandidateDetails(candidates);

            candidateDetails.setLocation_details(Map.of(location.getLocation_id(), location.getLocation_name()));
            candidateDetails.setCity_details(Map.of(city.getCity_id(), city.getCity_name()));
            candidateDetails.setState_details(Map.of(state.getState_id(), state.getState_name()));
            candidateDetails.setCountry_details(Map.of(country.getCountry_id(), country.getCountry_name()));





            candidateDetails.setApplication_status(candidateApplications.getApplication_status());
            List<CandidateDocuments> candidateDocumentsList = candidateDocumentsRepository.findByCandidateIdAndApplicationId(candidateApplications.getCandidate_id(),candidateApplications.getApplication_id());
            candidateDetails.setOfferLetterUrl(
                    candidateDocumentsList.stream()
                            .filter(doc -> "Offer Letter".equals(doc.getDocument_type()))
                            .map(CandidateDocuments::getFile_url)
                            .findFirst()
                            .orElse(null)
            );

            candidateDetailsList.add(candidateDetails);

        }
        return candidateDetailsList;
    }

    public List<CandidatesDTO> getDetailsByPositionId(UUID position_id) {
        List<CandidateApplications> candidateApplicationsList = candidateApplicationsRepository.findByPositionId(position_id);
        if (candidateApplicationsList.isEmpty()) {
            return null; // or throw an exception if no applications found
        }
        List<UUID> candidateIds = candidateApplicationsList.stream()
                .map(CandidateApplications::getCandidate_id)
                .filter(Objects::nonNull)
                .toList();
        List<Candidates> candidates = candidateRepository.findAllById(candidateIds);
//        for (CandidateApplications candidateApplications : candidateApplicationsList) {
//            Candidates candidates = candidateRepository.findById(candidateApplications.getCandidate_id()).orElse(null);
//            if (candidates != null) {
//                candidatesList.add(candidates);
//            }
//        }
        return CandidateMapper.toDTOs(candidates);
    }



    public Candidates getCandidatesById(UUID candidate_id) {
        return candidateRepository.findById(candidate_id).orElse(null);
    }


//    @Transactional
//    public String scheduleInterview(Interviewdto interviewdto) {
//        try {
//
//            if (interviewdto.getCandidate_id() == null || interviewdto.getPosition_id() == null) {
//                return "Invalid input data: Candidate, User, or Position ID is missing.";
//            }
//
//            UUID candidateId = interviewdto.getCandidate_id();
//            UUID positionId = interviewdto.getPosition_id();
//
//            Candidates candidate = candidateRepository.findById(candidateId).orElse(null);
//            if (candidate == null) {
//                return "Candidate not found!";
//            }
//
//
//            String interviewerName = interviewdto.getInterviewer_name();
//            String interviewerEmail = interviewdto.getInterviewer_email();
//
//            String positionTitle = positionRepository.findById(positionId)
//                    .map(Position::getPosition_title)
//                    .orElse("Unknown Position");
//
//            String candidateName = candidate.getFull_name();
//            String candidateEmail = candidate.getEmail();
//
//            String candidateFileUrl=candidate.getFile_url();
//
//            // Fetch application and validate status
//            List<CandidateApplications> applications = candidateApplicationsRepository
//                    .findByCandidateIdAndPositionId(candidateId, positionId);
//
//            if (applications.isEmpty()) {
//                return "No candidate application found for the selected position.";
//            }
//
//            CandidateApplications candidateApplication = applications.get(0);
//
//            if (!candidateApplication.getApplication_status().equalsIgnoreCase("Shortlisted")) {
//                return "Interview can only be scheduled for shortlisted candidates.";
//            }
//
//            // Send emails
//            Map<String, Object> emailData = new HashMap<>();
//            emailData.put("INTERVIEWER_NAME", interviewerName);
//            emailData.put("CANDIDATE_NAME", candidateName);
//            emailData.put("POSITION_TITLE", positionTitle);
//            emailData.put("DATE", interviewdto.getDate().toString());
//            emailData.put("TIME", interviewdto.getInterview_time().toString());
//
//            boolean candidateMailSent = sendEmailWithRetryMechanism(
//                    candidateEmail,
//                    "Interview Scheduled for " + positionTitle,
//                    "Candidate",
//                    emailData
//            );
//
//
//            boolean interviewerMailSent = sendEmailWithAttachmentRetry(
//                    interviewerEmail,
//                    "Interview Scheduled: " + candidateName + " for " + positionTitle,
//                    "Interviewer"
//                    ,emailData
//                    ,candidateFileUrl
//            );
//
//            if (!candidateMailSent || !interviewerMailSent) {
//                return "Failed to send one or both interview scheduling emails!";
//            }
//
//            // Update application status
//            candidateApplication.setUpdated_date(LocalDateTime.now());
//            candidateApplication.setApplication_status("Scheduled");
//            candidateApplicationsRepository.save(candidateApplication);
//
//            // Save interview
//            if (interviewdto.getDate() == null || interviewdto.getInterview_time() == null) {
//                throw new IllegalArgumentException("Date or Interview Time is missing!");
//            }
//
//            LocalDateTime scheduleTime = LocalDateTime.of(
//                    interviewdto.getDate(),
//                    interviewdto.getInterview_time()
//            );
//
//            Interviews interview = new Interviews();
//            interview.setCandidate_id(candidateId);
//            interview.setPosition_id(positionId);
//            interview.setType("Online");
//            interview.setSchedule_at(scheduleTime);
//            interview.setInterviewer(interviewerName);
//            interview.setStatus("Scheduled");
//
//            interviewerRepository.save(interview);
//
//            return "Interview Scheduled!";
//        }
//        catch (Exception e){
//            return "Interview not scheduled"+e.getMessage();
//        }
//    }

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
            // 1. Fetch candidate
            Candidates candidates = candidateRepository.findById(offerdto.getCandidate_id()).orElse(null);
            if (candidates == null) {
                return "Candidate not found!";
            }

            // 2. Fetch position title
            String positionTitle = positionRepository.findById(offerdto.getPosition_id())
                    .map(Position::getPosition_title)
                    .orElse("Unknown Position");

            // 3. Fetch candidate application
            List<CandidateApplications> applications = candidateApplicationsRepository
                    .findByCandidateIdAndPositionId(offerdto.getCandidate_id(), offerdto.getPosition_id());

            if (applications.isEmpty()) {
                return "Candidate application not found!";
            }

            CandidateApplications application = applications.get(0);

            // 4. Ensure candidate is "Scheduled"
//            if (!application.getApplication_status().equalsIgnoreCase("Scheduled")) {
//                return "Offer letter can only be sent to candidates with 'Scheduled' status.";
//            }

            // 5. Prepare email data
            Map<String, Object> offerDetails = new HashMap<>();
            offerDetails.put("COMPANY_NAME", "BANK OF BARODA!");
            offerDetails.put("CANDIDATE_NAME", candidates.getFull_name());
            offerDetails.put("POSITION_TITLE", positionTitle);
            offerDetails.put("PATH", offerdto.getOffer_letter_path());

            String email = candidates.getEmail();
            String path = offerdto.getOffer_letter_path();



            boolean candidateOfferLetterSent= sendEmailWithAttachmentRetry(email,"Offer Letter!","OfferLetter",offerDetails,path);



            if (!candidateOfferLetterSent) {
                return "Failed to send offer letter email.";
            }

            // 7. Save candidate document
            CandidateDocuments candidateDocuments = new CandidateDocuments();
            candidateDocuments.setDocument_id(UUID.randomUUID());
            candidateDocuments.setCandidate_id(offerdto.getCandidate_id());
            candidateDocuments.setApplication_id(application.getApplication_id());
            candidateDocuments.setDocument_type("Offer Letter");
            candidateDocuments.setFile_name(offerdto.getCandidate_id().toString() + ".pdf");
            candidateDocuments.setFile_url(path);
            candidateDocuments.setUploaded_date(LocalDateTime.now());
            candidateDocumentsRepository.save(candidateDocuments);

            // 8. Update application status
            application.setApplication_status("Offered");
            application.setUpdated_date(LocalDateTime.now());
            candidateApplicationsRepository.save(application);

            return "Offer sent!";
        } catch (Exception e) {
            return "Failed to send email due to: " + e.getMessage();
        }
    }



    public boolean sendEmailWithAttachmentRetry(String toEmail, String subject, String template, Map<String, Object> variables, String path) throws MessagingException, UnsupportedEncodingException {
       if(path==null || path.isEmpty()) {
           String res = mailService.sendSimpleEmail(toEmail, subject, template, variables);
           if (res.equals("Mail Sent!")) {
               return true;
           }
       }
        for (int i = 0; i < 3; i++) {
            String res = mailService.sendEmailWithAttachment(toEmail, subject, path, variables, template);
            if (res.equals("Mail Sent with attachment!")) {
                return true;
            }
        }
        return false;
    }

    public List<CandidateDetails> getCandidateByApplicationStatus(String status) {
        List<CandidateDetails> candidateDetailsList = new ArrayList<>();

        for (CandidateApplications candidateApplications : candidateApplicationsRepository.findAll()) {
            CandidateDetails candidateDetails = new CandidateDetails();
            UUID position_id=candidateApplications.getPosition_id();
            if (candidateApplications.getApplication_status().equals(status)) {
                List<Long> locationIds = jobPostingLocationRepository.findByPositionId(position_id);
                if (!locationIds.isEmpty()) {
                    locationRepository.findById(locationIds.get(0)).ifPresent(location -> {
                        candidateDetails.setLocation_details(Map.of(location.getLocation_id(), location.getLocation_name()));
                        cityRepository.findById(location.getCity_id()).ifPresent(city -> {
                            candidateDetails.setCity_details(Map.of(city.getCity_id(), city.getCity_name()));
                            stateRepository.findById(city.getState_id()).ifPresent(state -> {
                                candidateDetails.setState_details(Map.of(state.getState_id(), state.getState_name()));
                                countryRepository.findById(state.getCountry_id()).ifPresent(country ->
                                        candidateDetails.setCountry_details(Map.of(country.getCountry_id(), country.getCountry_name()))
                                );
                            });
                        });
                    });
                }
                if(candidateApplications.getCandidate_id()==null){
                    continue;
                }
                UUID candidateId=candidateApplications.getCandidate_id();
                Candidates candidate=candidateRepository.findById(candidateId).get();
                candidateDetails.setCandidate_id(candidate.getCandidate_id());
                candidateDetails.setFull_name(candidate.getFull_name());
                candidateDetails.setEmail(candidate.getEmail());
                candidateDetails.setPhone(candidate.getPhone());
                candidateDetails.setDate_of_birth(candidate.getDate_of_birth());
                candidateDetails.setGender(candidate.getGender());
                candidateDetails.setId_proof(candidate.getId_proof());
                candidateDetails.setNationality_id(candidate.getNationality_id());
                candidateDetails.setReservation_category_id(candidate.getReservation_category_id());
                candidateDetails.setSpecial_category_id(candidate.getSpecial_category_id());
                candidateDetails.setHighest_qualification_id(candidate.getHighest_qualification_id());
                candidateDetails.setTotal_experience(candidate.getTotal_experience());
                candidateDetails.setAddress(candidate.getAddress());
                candidateDetails.setComments(candidate.getComments());
                candidateDetails.setSkills(candidate.getSkills());
                candidateDetails.setCurrent_designation(candidate.getCurrent_designation());
                candidateDetails.setCurrent_employer(candidate.getCurrent_employer());
                candidateDetails.setFile_url(candidate.getFile_url());
                candidateDetails.setFileUrl(candidate.getFile_url());
                candidateDetails.setEducation_qualification(candidate.getEducation_qualification());
                candidateDetails.setDocumentUrl(candidate.getDocumentUrl());
                candidateDetails.setRank(candidate.getRank());
                candidateDetails.setApplication_status(candidateApplications.getApplication_status());
                if (candidateApplications.getApplication_status().equals(status)) {
                    candidateDetails.setApplication_status(candidateApplications.getApplication_status());
                } else {
                    continue;
                }
                candidateDetailsList.add(candidateDetails);
            }

        }
        return candidateDetailsList;
    }

    public Integer countCandidatesByStatus(String status) {
        return getCandidateByApplicationStatus(status).size();
    }


    //Method to get all candidate details
    public List<CandidateDetails> getAllCandidateDetails() {
        List<CandidateDetails> candidateDetailsList = new ArrayList<>();
        List<CandidateApplications> candidateApplicationsList =candidateApplicationsRepository.findAll();
        for (CandidateApplications candidateApplications : candidateApplicationsList ) {
            CandidateDetails candidateDetails = new CandidateDetails();
            UUID position_id=candidateApplications.getPosition_id();
            List<Long> locationIds = jobPostingLocationRepository.findByPositionId(position_id);
            if (!locationIds.isEmpty()) {
                locationRepository.findById(locationIds.get(0)).ifPresent(location -> {
                    candidateDetails.setLocation_details(Map.of(location.getLocation_id(), location.getLocation_name()));
                    cityRepository.findById(location.getCity_id()).ifPresent(city -> {
                        candidateDetails.setCity_details(Map.of(city.getCity_id(), city.getCity_name()));
                        stateRepository.findById(city.getState_id()).ifPresent(state -> {
                            candidateDetails.setState_details(Map.of(state.getState_id(), state.getState_name()));
                            countryRepository.findById(state.getCountry_id()).ifPresent(country ->
                                    candidateDetails.setCountry_details(Map.of(country.getCountry_id(), country.getCountry_name()))
                            );
                        });
                    });
                });
            }
            if(candidateApplications.getCandidate_id()==null){
                continue;
            }
            UUID candidateId=candidateApplications.getCandidate_id();
            Candidates candidates=candidateRepository.findById(candidateId).get();


            candidateDetails.setCandidate_id(candidateId);
            candidateDetails.setFull_name(candidates.getFull_name());
            candidateDetails.setUsername(candidates.getUsername());
            candidateDetails.setEmail(candidates.getEmail());
            candidateDetails.setPhone(candidates.getPhone());
            candidateDetails.setGender(candidates.getGender());
            candidateDetails.setReservation_category_id(candidates.getReservation_category_id());
            candidateDetails.setHighest_qualification(candidates.getHighest_qualification_id());
            candidateDetails.setTotal_experience(candidates.getTotal_experience());
            candidateDetails.setAddress(candidates.getAddress());
            candidateDetails.setSpecial_category_id(candidates.getSpecial_category_id());
            String fileUrl=candidates.getFile_url();
            candidateDetails.setFileUrl(fileUrl);
            candidateDetails.setDocumentUrl(candidates.getDocumentUrl());
            candidateDetails.setApplication_status(candidateApplications.getApplication_status());
            candidateDetailsList.add(candidateDetails);
        }
        return candidateDetailsList;
    }

    public Interviews getInterviewDetailsByInterviewId(Integer interview_id){
        return interviewerRepository.findById(interview_id).orElse(null);
    }


    public InterviewerResponse getInterviewsByCandidateAndPositionId(UUID candidateId, UUID positionId) throws Exception {
        try {
            List<Interviews> interviews = interviewerRepository.findByCandidateIdAndPositionIdNative(candidateId, positionId);

            if (interviews == null || interviews.isEmpty()) {
                return null;
            }

            Interviews interview = interviews.get(0);

            Users user = usersRepository.findById(interview.getUserId()).orElse(null);

            InterviewerResponse interviewerResponse = new InterviewerResponse();
            interviewerResponse.setInterview_id(interview.getInterview_id());
            interviewerResponse.setCandidate_id(interview.getCandidate_id());
            interviewerResponse.setPosition_id(interview.getPosition_id());
            interviewerResponse.setType(interview.getType());
            interviewerResponse.setStatus(interview.getStatus());
            interviewerResponse.setScheduled_at(interview.getSchedule_at());
            interviewerResponse.setInterviewer(interview.getInterviewer());
            interviewerResponse.setUser_id(interview.getUserId());
            interviewerResponse.setInterviewer_email(user != null ? user.getEmail() : null);

            return interviewerResponse;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Couldn't retrieve interview due to: " + e.getMessage());
        }
    }


//    public String getStatus(InterviewDetails interviewDetails){
//
//        if(interviewDetails.getStatus().equals("Scheduled")){
//            return scheduleInterview(new Interviewdto(
//                    interviewDetails.getCandidate_id(),
//                    interviewDetails.getDate(),
//                    interviewDetails.getTime(),
//                    interviewDetails.getPosition_id(),
//                    interviewDetails.getUser_id(),
//                    interviewDetails.getInterviewer_name(),
//                    interviewDetails.getInterviewer_email()
//            ));
//        }
//        else if (interviewDetails.getStatus().equals("Rescheduled")) {
//            Interviews interviews = interviewerRepository.findByCandidateIdAndPositionIdNative(interviewDetails.getCandidate_id(), interviewDetails.getPosition_id()).get(0);
//            UUID candidate_id = interviewDetails.getCandidate_id();
//            Candidates candidates = candidateRepository.findById(candidate_id).orElse(null);
//            if (candidates == null) {
//                return "Candidate not found!";
//            }
//            String candidateName = candidates.getFull_name();
//            String candidateEmail = candidates.getEmail();
//            UUID position_id = interviewDetails.getPosition_id();
//            Users users = usersRepository.findByRole("Interviewer").get(0);
//            Position position = positionRepository.findById(position_id).orElse(null);
//            if (position == null) {
//                return "Position not found!";
//            }
//            String positionTitle = position.getPosition_title();
//            Map<String, Object> emailData = new HashMap<>();
//            emailData.put("INTERVIEWER_NAME", users.getName());
//            emailData.put("CANDIDATE_NAME", candidateName);
//            emailData.put("POSITION_TITLE", positionTitle);
//            emailData.put("DATE", interviewDetails.getDate().toString());
//            emailData.put("TIME", interviewDetails.getTime().toString());
//            boolean candidateMailSent = sendEmailWithRetryMechanism(
//                    candidateEmail,
//                    "Interview Rescheduled for " + positionTitle,
//                    "CandidateRescheduled",
//                    emailData
//            );
//
//            boolean interviewerMailSent = sendEmailWithRetryMechanism(
//                    users.getEmail(),
//                    "Interview Rescheduled: " + candidateName + " for " + positionTitle,
//                    "InterviewerRescheduled",
//                    emailData
//            );
//
//            if (!candidateMailSent || !interviewerMailSent) {
//                return "Failed to send one or both interview scheduling emails!";
//            }
//
//            CandidateApplications candidateApplications = candidateApplicationsRepository.findByCandidateIdAndPositionId(candidate_id, position_id).get(0);
//
//            Interviews interviews1 = interviews;
//            LocalDateTime scheduleTime = LocalDateTime.of(
//                    interviewDetails.getDate(),
//                    interviewDetails.getTime()
//            );
//            LocalDateTime currentTime = LocalDateTime.now();
//            interviews1.setSchedule_at(scheduleTime);
//            interviews1.setStatus("Rescheduled");
//
//            candidateApplications.setApplication_status("Rescheduled");
//            candidateApplications.setUpdated_date(currentTime);
//            candidateApplicationsRepository.save(candidateApplications);
//            interviewerRepository.save(interviews1);
//            return "Interview Rescheduled!";
//        } else {
//            Interviews interviews=interviewerRepository.findByCandidateIdAndPositionIdNative(interviewDetails.getCandidate_id(), interviewDetails.getPosition_id()).get(0);
//            if(interviews==null){
//                return "No interview found for the given candidate and position!";
//            }
//            UUID candidate_id=interviewDetails.getCandidate_id();
//            Candidates candidates=candidateRepository.findById(candidate_id).orElse(null);
//            if(candidates==null){
//                return "Candidate not found!";
//            }
//            String candidateName = candidates.getFull_name();
//            String candidateEmail = candidates.getEmail();
//            UUID position_id=interviewDetails.getPosition_id();
//            Users users=usersRepository.findByRole("Interviewer").get(0);
//            Position position=positionRepository.findById(position_id).orElse(null);
//            if(position==null){
//                return "Position not found!";
//            }
//            String positionTitle = position.getPosition_title();
//            Map<String, Object> emailData = new HashMap<>();
//            emailData.put("INTERVIEWER_NAME", users.getName());
//            emailData.put("CANDIDATE_NAME", candidateName);
//            emailData.put("POSITION_TITLE", positionTitle);
//            emailData.put("DATE", interviewDetails.getDate().toString());
//            emailData.put("TIME", interviewDetails.getTime().toString());
//
//            Interviews interviews1= interviews;
//
//            if(interviews1!=null){
//                boolean candidateMailSent = sendEmailWithRetryMechanism(
//                        candidateEmail,
//                        "Interview Cancelled for " + positionTitle,
//                        "CandidateCancelled",
//                        emailData
//                );
//
//
//                boolean interviewerMailSent = sendEmailWithRetryMechanism(
//                        users.getEmail(),
//                        "Interview Cancelled: " + candidateName + " for " + positionTitle,
//                        "InterviewerCancelled",
//                        emailData
//                );
//
//
//
//                if (!candidateMailSent || !interviewerMailSent) {
//                    return "Failed to send one or both interview scheduling emails!";
//                }
//                CandidateApplications candidateApplications =candidateApplicationsRepository.findByCandidateIdAndPositionId(candidate_id, position_id).get(0);
//                LocalDateTime currentTime = LocalDateTime.now();
//                candidateApplications.setApplication_status("Cancelled");
//                interviews1.setStatus("Cancelled");
//                candidateApplications.setUpdated_date(currentTime);
//                interviewerRepository.save(interviews1);
//                candidateApplicationsRepository.save(candidateApplications);
//                return "Interview Cancelled!";
//            }
//        }
//        return "Invalid Status!";
//    }

    @Transactional
    public String scheduleInterview(InterviewDetails interviewDetails) throws MessagingException, UnsupportedEncodingException {
        List<Interviews> interviewsList = interviewerRepository
                .findByCandidateIdAndPositionIdNative(interviewDetails.getCandidate_id(), interviewDetails.getPosition_id());
        Interviews interviews1;


        System.out.println(interviewDetails.getUser_id());
        if (interviewsList.isEmpty()) {
            interviews1 = new Interviews();
            interviews1.setCandidate_id(interviewDetails.getCandidate_id());
            interviews1.setPosition_id(interviewDetails.getPosition_id());
            interviews1.setType("Online");

            ;
        } else {
            interviews1 = interviewsList.get(0);
        }

        Candidates candidates = candidateRepository.findById(interviewDetails.getCandidate_id()).orElse(null);

        String candidateEmail = candidates.getEmail();

        //get user based on role interviewer
        Users users = usersRepository.findById(interviewDetails.getUser_id()).orElse(null);

        if(users==null){
            throw new RuntimeException("Interviewer not found!");
        }

        Position position = positionRepository.findById(interviewDetails.getPosition_id()).orElse(null);
        CandidateApplications candidateApplications =candidateApplicationsRepository.findByCandidateIdAndPositionId(interviewDetails.getCandidate_id(), interviewDetails.getPosition_id()).get(0);


        // seting values to mail data
        Map<String, Object> emailData = new HashMap<>();
        emailData.put("INTERVIEWER_NAME", users.getName());
        emailData.put("CANDIDATE_NAME", candidates.getFull_name());
        emailData.put("POSITION_TITLE",position.getPosition_title());
        emailData.put("DATE", interviewDetails.getDate().toString());
        emailData.put("TIME", interviewDetails.getTime().toString());
        boolean candidateMailSent = false;
        boolean interviewerMailSent = false;
        String candidateResume = candidates.getFile_url();
        String subject= "";
        String template= "";

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime scheduleTime = LocalDateTime.of(
                interviewDetails.getDate(),
                interviewDetails.getTime()
        );
        interviews1.setStatus(interviewDetails.getStatus());
        interviews1.setUserId(interviewDetails.getUser_id());

        if(interviewDetails.getStatus().equals("Scheduled")){
             subject = "Interview Scheduled for " + position.getPosition_title();
             template = "Candidate";
             interviews1.setSchedule_at(scheduleTime);


        }else if (interviewDetails.getStatus().equals("Rescheduled")) {
            subject = "Interview Rescheduled for " + position.getPosition_title();
            template = "CandidateRescheduled";
            interviews1.setSchedule_at(scheduleTime);

        }else if (interviewDetails.getStatus().equals("Cancelled")) {
            subject = "Interview Cancelled for " + position.getPosition_title();
            template = "CandidateCancelled";
        }

        candidateMailSent = sendEmailWithRetryMechanism(
                candidateEmail,
                subject,
                template,
                emailData
        );
        interviewerMailSent = sendEmailWithAttachmentRetry(
                users.getEmail(),
                subject,
                template,
                emailData,
                candidateResume
        );

        if (!candidateMailSent || !interviewerMailSent) {
            throw new RuntimeException("Failed to send one or both interview scheduling emails!");
        }

//        interviews1.setInterview_id(interviewDetails.getUser_id());
        interviews1.setInterviewer(interviewDetails.getInterviewer_name());

        candidateApplications.setApplication_status(interviewDetails.getStatus());
        candidateApplications.setUpdated_date(currentTime);
        interviewerRepository.save(interviews1);
        candidateApplicationsRepository.save(candidateApplications);
        return "Interview "+interviewDetails.getStatus()+" sucessfully !";
    }

    public String applyInterview(UUID candidateId, UUID positionId) {
        try {
            CandidateApplications candidateApplications = new CandidateApplications();
            candidateApplications.setApplication_id(UUID.randomUUID());
            candidateApplications.setCandidate_id(candidateId);
            candidateApplications.setPosition_id(positionId);
            candidateApplications.setApplication_status("Shortlisted");
            LocalDateTime time = LocalDateTime.now();

            candidateApplications.setApplication_date(time);
            candidateApplications.setUpdated_date(time);
            candidateApplicationsRepository.save(candidateApplications);
            return "Applied for Job!";
        }catch (Exception e){
            return " Couldn't Apply!";
        }
    }


    public List<JobPositionsDTO> getAllDetailsByCandidateId(UUID candidate_id){
        List<CandidateApplications> candidateApplicationsList=candidateApplicationsRepository.findByCandidateIdNative(candidate_id);
        List<UUID> positionIds=candidateApplicationsList.stream().map(CandidateApplications::getPosition_id).toList();

        ResponseEntity<ApiResponse<List<JobPositionsDTO>>> responseDTOS=feignPositionDTO.getActiveJobs();
        List<JobPositionsDTO> jobPositionsDTOList=responseDTOS.getBody().getData();
        return jobPositionsDTOList.stream().filter(jobPositionsDTO -> positionIds.contains(jobPositionsDTO.getPosition_id())).toList();
    }

    public CandidatesDTO updateCandidate(CandidatesDTO candidate) {
        try {
            Candidates existingCandidate = candidateRepository.findById(candidate.getCandidate_id())
                    .orElseThrow(() -> new RuntimeException("Candidate not found"));

            // Update candidate details
            existingCandidate.setFull_name(candidate.getFull_name());
            existingCandidate.setDate_of_birth(candidate.getDate_of_birth());
            existingCandidate.setPhone(candidate.getPhone());
            existingCandidate.setId_proof(candidate.getId_proof());
            existingCandidate.setGender(candidate.getGender());
            existingCandidate.setNationality_id(candidate.getNationality_id());
            existingCandidate.setReservation_category_id(1); // Assuming 1 is the default value for reservation category
            existingCandidate.setSpecial_category_id(1); // Assuming 1 is the default value for special category
            existingCandidate.setHighest_qualification_id(null);
            existingCandidate.setAddress(candidate.getAddress());
            existingCandidate.setTotal_experience(candidate.getTotal_experience());
            existingCandidate.setComments(candidate.getComments());
            existingCandidate.setSkills(candidate.getSkills());
            existingCandidate.setCurrent_designation(candidate.getCurrent_designation());
            existingCandidate.setCurrent_employer(candidate.getCurrent_employer());
            existingCandidate.setFile_url(candidate.getFile_url());
            existingCandidate.setEducation_qualification(candidate.getEducation_qualification());
            existingCandidate.setDocumentUrl(candidate.getDocumentUrl());
            // Save updated candidate
            Candidates updatedCandidate = candidateRepository.save(existingCandidate);

            // Convert to DTO and return
            return new CandidatesDTO(updatedCandidate);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't create candidate due to: " + e.getMessage());
        }
    }


    @Transactional
    public String submitFeedback(FeedbackDto feedbackDto) {
        try {
            // 1. Get candidate application safely
            List<CandidateApplications> apps = candidateApplicationsRepository
                    .findByCandidateIdAndPositionId(feedbackDto.getCandidateId(), feedbackDto.getPositionId());

            if (apps.isEmpty()) {
                throw new RuntimeException("Candidate application not found!");
            }
            CandidateApplications candidateApplications = apps.get(0);

            // 2. Create workflow approval
            WorkflowApprovalEntity workflowApproval = new WorkflowApprovalEntity();
            workflowApproval.setApprovalId(UUID.randomUUID());
            workflowApproval.setEntityId(candidateApplications.getApplication_id());
            workflowApproval.setApproverRole("Interviewer");
            workflowApproval.setApproverId(feedbackDto.getInterviewerId());
            workflowApproval.setActionDate(LocalDateTime.now());
            workflowApproval.setComments(feedbackDto.getComments());
            workflowApproval.setStatus(feedbackDto.getStatus());
            workflowApproval.setEntityType("interviews");
            workflowApproval.setStepNumber(1);

            // 3. Get interview safely
            List<Interviews> interviewList = interviewerRepository
                    .findByCandidateIdAndPositionIdNative(feedbackDto.getCandidateId(), feedbackDto.getPositionId());

            if (interviewList.isEmpty()) {
                throw new RuntimeException("Interview not found for candidate!");
            }
            Interviews interviews = interviewList.get(0);
            interviews.setStatus(feedbackDto.getStatus());

            // 4. Update candidate applications
            candidateApplications.setApplication_status(feedbackDto.getStatus());
            candidateApplications.setUpdated_date(LocalDateTime.now());

            // 5. Save all entities (transaction will commit only if all succeed)
            interviewerRepository.save(interviews);
            candidateApplicationsRepository.save(candidateApplications);
            workflowApprovalEntityRepository.save(workflowApproval);

            return "Feedback submitted successfully!";
        } catch (Exception e) {
            // Log full stack trace for debugging

            throw new RuntimeException("Couldn't submit feedback: " + e.getMessage(), e);
        }
    }

    public List<FeedbackResponse> getFeedbackByCandidateAndPositionId(UUID candidate_id,UUID position_id){
        try{
            List<FeedbackResponse> feedbackResponse = new ArrayList<>();
            CandidateApplications candidateApplications=candidateApplicationsRepository.findByCandidateIdAndPositionId(candidate_id,position_id).get(0);
            if(candidateApplications==null){
                return null;
            }

            List<WorkflowApprovalEntity> workflowApprovalEntity=workflowApprovalEntityRepository.findByEntityId(candidateApplications.getApplication_id());
            List<Integer> userIds=workflowApprovalEntity.stream().map(WorkflowApprovalEntity::getApproverId).toList();
            List<Users> usersList=usersRepository.findAllById(userIds);

            for(WorkflowApprovalEntity workflowApproval:workflowApprovalEntity){
                FeedbackResponse feedback=new FeedbackResponse();
                feedback.setApprovalId(workflowApproval.getApprovalId());
                feedback.setComments(workflowApproval.getComments());
                feedback.setStatus(workflowApproval.getStatus());
                feedback.setActionDate(workflowApproval.getActionDate());
                String userName = usersList.stream().filter(user -> user.getUserid().equals(workflowApproval.getApproverId()))
                        .map(Users::getName)
                        .findFirst()
                        .orElse("Unknown User");
                feedback.setInterviewerName(userName);

                feedbackResponse.add(feedback);
            }

            return feedbackResponse;
        }catch (Exception e){
            return null;
        }
    }

    public FeedbackResponse updateFeedback(UUID approvalId, String comments, String status) throws Exception {
        try {
            WorkflowApprovalEntity workflowApproval = workflowApprovalEntityRepository.findById(approvalId)
                    .orElseThrow(() -> new Exception("Feedback with ID " + approvalId + " not found"));

            workflowApproval.setComments(comments);
            workflowApproval.setStatus(status);
            workflowApprovalEntityRepository.save(workflowApproval);

            FeedbackResponse feedbackResponse = new FeedbackResponse();
            feedbackResponse.setApprovalId(workflowApproval.getApprovalId());
            feedbackResponse.setComments(workflowApproval.getComments());
            feedbackResponse.setStatus(workflowApproval.getStatus());
            feedbackResponse.setActionDate(workflowApproval.getActionDate());

            Users user = usersRepository.findById(workflowApproval.getApproverId())
                    .orElse(null);
            if (user != null) {
                feedbackResponse.setInterviewerName(user.getName());
            } else {
                feedbackResponse.setInterviewerName("Unknown User");
            }

            return feedbackResponse;
        } catch (Exception e) {
            throw new Exception("Error updating feedback: " + e.getMessage());
        }
    }
}
