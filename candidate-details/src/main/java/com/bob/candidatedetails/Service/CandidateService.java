package com.bob.candidatedetails.Service;

import com.bob.candidatedetails.Feign.FeignPositionDTO;
import com.bob.db.entity.City;
import com.bob.db.entity.Country;
import com.bob.db.entity.Location;
import com.bob.db.entity.State;
import com.bob.db.repository.CityRepository;
import com.bob.db.repository.CountryRepository;
import com.bob.db.repository.LocationRepository;
import com.bob.db.repository.StateRepository;
import com.bob.db.repository.*;
import com.bob.db.dto.ApiResponse;
import com.bob.db.dto.*;
import com.bob.db.entity.*;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FeignPositionDTO feignPositionDTO;

    public List<CandidateDetails> getCandidateDetailsByPositionId(UUID position_id) {
        List<CandidateApplicationsEntity> candidateApplicationsList = candidateApplicationsRepository.findByPositionId(position_id);
        List<CandidateDetails> candidateDetailsList = new ArrayList<>();
        for (CandidateApplicationsEntity candidateApplications : candidateApplicationsList) {
            CandidateDetails candidateDetails = new CandidateDetails();
//            List<FileInfo> fileInfoList = candidateDocumentsRepository.getFileDetails(candidateApplications.getCandidate_id());
            String fileUrl=candidateRepository.findById(candidateApplications.getCandidateId()).get().getFile_url();
            List<Long> locationIds= jobPostingLocationRepository.findByPositionId(position_id);
            Location location=locationRepository.findById(locationIds.get(0)).orElse(null);
            candidateDetails.setLocation_details(Map.of(location.getLocation_id(), location.getLocation_name()));
            City city= cityRepository.findById(location.getCity_id()).orElse(null);
            candidateDetails.setCity_details(Map.of(city.getCity_id(), city.getCity_name()));
            State state = stateRepository.findById(city.getState_id()).orElse(null);
            candidateDetails.setState_details(Map.of(state.getState_id(), state.getState_name()));
            Country country = countryRepository.findById(state.getCountry_id()).orElse(null);
            candidateDetails.setCountry_details(Map.of(country.getCountry_id(), country.getCountry_name()));
            UUID candidateId=candidateApplications.getCandidateId();
            Candidates candidates=candidateRepository.findById(candidateApplications.getCandidateId()).get();

            candidateDetails.setCandidate_id(candidateId);
            candidateDetails.setFull_name(candidates.getFull_name());
            candidateDetails.setUsername(candidates.getUsername());
            candidateDetails.setEmail(candidates.getEmail());
            candidateDetails.setPhone(candidates.getPhone());
            candidateDetails.setReservation_category_id(candidates.getReservation_category_id());
            candidateDetails.setHighest_qualification(candidates.getHighest_qualification_id());
            candidateDetails.setTotal_experience(candidates.getTotal_experience());
            candidateDetails.setAddress(candidates.getAddress());
            candidateDetails.setGender(candidates.getGender());
            candidateDetails.setSpecial_category_id(candidates.getSpecial_category_id());
            candidateDetails.setFileUrl(fileUrl);
            candidateDetails.setApplication_status(candidateApplications.getApplicationStatus());

            candidateDetailsList.add(candidateDetails);

        }
        return candidateDetailsList;
    }

    public List<Candidates> getDetailsByPositionId(UUID position_id) {
        List<CandidateApplicationsEntity> candidateApplicationsList = candidateApplicationsRepository.findByPositionId(position_id);
        if (candidateApplicationsList.isEmpty()) {
            return null; // or throw an exception if no applications found
        }
        List<Candidates> candidatesList = new ArrayList<>();
        for (CandidateApplicationsEntity candidateApplications : candidateApplicationsList) {
            Candidates candidates = candidateRepository.findById(candidateApplications.getCandidateId()).orElse(null);
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
        try {
            if (interviewdto.getCandidate_id() == null || interviewdto.getPosition_id() == null) {
                return "Invalid input data: Candidate, User, or Position ID is missing.";
            }

            UUID candidateId = interviewdto.getCandidate_id();
            UUID positionId = interviewdto.getPosition_id();

            Candidates candidate = candidateRepository.findById(candidateId).orElse(null);
            if (candidate == null) {
                return "Candidate not found!";
            }

            List<Users> interviewers = usersRepository.findByRole("Interviewer");
            if (interviewers.isEmpty()) {
                return "No interviewer found!";
            }

            Users interviewer = interviewers.get(0);
            String interviewerName = interviewer.getName();
            String interviewerEmail = interviewer.getEmail();

            String positionTitle = positionRepository.findById(positionId)
                    .map(Position::getPosition_title)
                    .orElse("Unknown Position");

            String candidateName = candidate.getFull_name();
            String candidateEmail = candidate.getEmail();

            String candidateFileUrl=candidate.getFile_url();

            // Fetch application and validate status
            List<CandidateApplicationsEntity> applications = candidateApplicationsRepository
                    .findByCandidateIdAndPositionId(candidateId, positionId);

            if (applications.isEmpty()) {
                return "No candidate application found for the selected position.";
            }

            CandidateApplicationsEntity candidateApplication = applications.get(0);

            if (!candidateApplication.getApplicationStatus().equalsIgnoreCase("Shortlisted")) {
                return "Interview can only be scheduled for shortlisted candidates.";
            }

            // Send emails
            Map<String, Object> emailData = new HashMap<>();
            emailData.put("INTERVIEWER_NAME", interviewerName);
            emailData.put("CANDIDATE_NAME", candidateName);
            emailData.put("POSITION_TITLE", positionTitle);
            emailData.put("DATE", interviewdto.getDate().toString());
            emailData.put("TIME", interviewdto.getInterview_time().toString());

            boolean candidateMailSent = sendEmailWithRetryMechanism(
                    candidateEmail,
                    "Interview Scheduled for " + positionTitle,
                    "Candidate",
                    emailData
            );



            boolean interviewerMailSent = sendEmailWithAttachmentRetry(
                    interviewerEmail,
                    "Interview Scheduled: " + candidateName + " for " + positionTitle,
                    "Interviewer"
                    ,emailData
                    ,candidateFileUrl
            );

            if (!candidateMailSent || !interviewerMailSent) {
                return "Failed to send one or both interview scheduling emails!";
            }

            // Update application status
            candidateApplication.setUpdatedDate(LocalDateTime.now());
            candidateApplication.setApplicationStatus("Scheduled");
            candidateApplicationsRepository.save(candidateApplication);

            // Save interview
            if (interviewdto.getDate() == null || interviewdto.getInterview_time() == null) {
                throw new IllegalArgumentException("Date or Interview Time is missing!");
            }

            LocalDateTime scheduleTime = LocalDateTime.of(
                    interviewdto.getDate(),
                    interviewdto.getInterview_time()
            );

            Interviews interview = new Interviews();
            interview.setCandidate_id(candidateId);
            interview.setPosition_id(positionId);
            interview.setType("Online");
            interview.setSchedule_at(scheduleTime);
            interview.setInterviewer(interviewerName);
            interview.setStatus("Scheduled");

            interviewerRepository.save(interview);

            return "Interview Scheduled!";
        }
        catch (Exception e){
            return "Interview not scheduled"+e.getMessage();
        }
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
            List<CandidateApplicationsEntity> applications = candidateApplicationsRepository
                    .findByCandidateIdAndPositionId(offerdto.getCandidate_id(), offerdto.getPosition_id());

            if (applications.isEmpty()) {
                return "Candidate application not found!";
            }

            CandidateApplicationsEntity application = applications.get(0);

            // 4. Ensure candidate is "Scheduled"
            if (!application.getApplicationStatus().equalsIgnoreCase("Scheduled")) {
                return "Offer letter can only be sent to candidates with 'Scheduled' status.";
            }

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
            candidateDocuments.setApplication_id(application.getApplicationId());
            candidateDocuments.setDocument_type("Offer Letter");
            candidateDocuments.setFile_name(offerdto.getCandidate_id().toString() + ".pdf");
            candidateDocuments.setFile_url(path);
            candidateDocuments.setUploaded_date(LocalDateTime.now());
            candidateDocumentsRepository.save(candidateDocuments);

            // 8. Update application status
            application.setApplicationStatus("Offered");
            application.setUpdatedDate(LocalDateTime.now());
            candidateApplicationsRepository.save(application);

            return "Offer sent!";
        } catch (Exception e) {
            return "Failed to send email due to: " + e.getMessage();
        }
    }



    public boolean sendEmailWithAttachmentRetry(String toEmail, String subject, String template, Map<String, Object> variables, String path) throws MessagingException, UnsupportedEncodingException {
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

        for (CandidateApplicationsEntity candidateApplications : candidateApplicationsRepository.findAll()) {
            CandidateDetails candidateDetails = new CandidateDetails();
            UUID position_id=candidateApplications.getPositionId();
            if (candidateApplications.getApplicationStatus().equals(status)) {
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
                if(candidateApplications.getCandidateId()==null){
                    continue;
                }
                UUID candidateId=candidateApplications.getCandidateId();
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
                candidateDetails.setApplication_status(candidateApplications.getApplicationStatus());

                if (candidateApplications.getApplicationStatus().equals(status)) {
                    candidateDetails.setApplication_status(candidateApplications.getApplicationStatus());
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
        for (CandidateApplicationsEntity candidateApplications : candidateApplicationsRepository.findAll()) {
            CandidateDetails candidateDetails = new CandidateDetails();
            UUID position_id=candidateApplications.getPositionId();
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
            if(candidateApplications.getCandidateId()==null){
                continue;
            }
            UUID candidateId=candidateApplications.getCandidateId();
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
            candidateDetails.setApplication_status(candidateApplications.getApplicationStatus());
            candidateDetailsList.add(candidateDetails);
        }
        return candidateDetailsList;
    }

    public Interviews getInterviewDetailsByInterviewId(Integer interview_id){
        return interviewerRepository.findById(interview_id).orElse(null);
    }


    public Interviews getInterviewsByCandidateAndPositionId(UUID candidate_id,UUID position_id){
    try{
        List<Interviews> interviewsList=interviewerRepository.findByCandidateIdAndPositionIdNative(candidate_id,position_id);
        return interviewsList.get(0);
    }catch (Exception e){
        System.out.println(e.getMessage());
        return null;
    }

    }

    public String getStatus(InterviewDetails interviewDetails){

        if(interviewDetails.getStatus().equals("Scheduled")){
            return scheduleInterview(new Interviewdto(
                    interviewDetails.getCandidate_id(),
                    interviewDetails.getDate(),
                    interviewDetails.getTime(),
                    interviewDetails.getPosition_id()
            ));
        }
        else if (interviewDetails.getStatus().equals("Rescheduled")){
            Interviews interviews=interviewerRepository.findByCandidateIdAndPositionIdNative(interviewDetails.getCandidate_id(), interviewDetails.getPosition_id()).get(0);
            UUID candidate_id=interviewDetails.getCandidate_id();
            Candidates candidates=candidateRepository.findById(candidate_id).orElse(null);
            if(candidates==null){
                return "Candidate not found!";
            }
            String candidateName = candidates.getFull_name();
            String candidateEmail = candidates.getEmail();
            UUID position_id=interviewDetails.getPosition_id();
            Users users=usersRepository.findByRole("Interviewer").get(0);
            Position position=positionRepository.findById(position_id).orElse(null);
            if(position==null){
                return "Position not found!";
            }
            String positionTitle = position.getPosition_title();
            Map<String, Object> emailData = new HashMap<>();
            emailData.put("INTERVIEWER_NAME", users.getName());
            emailData.put("CANDIDATE_NAME", candidateName);
            emailData.put("POSITION_TITLE", positionTitle);
            emailData.put("DATE", interviewDetails.getDate().toString());
            emailData.put("TIME", interviewDetails.getTime().toString());
            boolean candidateMailSent = sendEmailWithRetryMechanism(
                    candidateEmail,
                    "Interview Rescheduled for " + positionTitle,
                    "CandidateRescheduled",
                    emailData
            );

            boolean interviewerMailSent = sendEmailWithRetryMechanism(
                    users.getEmail(),
                    "Interview Rescheduled: " + candidateName + " for " + positionTitle,
                    "InterviewerRescheduled",
                    emailData
            );

            if (!candidateMailSent || !interviewerMailSent) {
                return "Failed to send one or both interview scheduling emails!";
            }
            CandidateApplicationsEntity candidateApplications=candidateApplicationsRepository.findByCandidateIdAndPositionId(candidate_id, position_id).get(0);

            Interviews interviews1=interviews;
                LocalDateTime scheduleTime = LocalDateTime.of(
                        interviewDetails.getDate(),
                        interviewDetails.getTime()
                );
                LocalDateTime currentTime = LocalDateTime.now();
                interviews1.setSchedule_at(scheduleTime);
                interviews1.setStatus("Rescheduled");

                candidateApplications.setApplicationStatus("Rescheduled");
                candidateApplications.setUpdatedDate(currentTime);
                candidateApplicationsRepository.save(candidateApplications);
                interviewerRepository.save(interviews1);
                return "Interview Rescheduled!";
        }
        else {
            Interviews interviews=interviewerRepository.findByCandidateIdAndPositionIdNative(interviewDetails.getCandidate_id(), interviewDetails.getPosition_id()).get(0);
            if(interviews==null){
                return "No interview found for the given candidate and position!";
            }
            UUID candidate_id=interviewDetails.getCandidate_id();
            Candidates candidates=candidateRepository.findById(candidate_id).orElse(null);
            if(candidates==null){
                return "Candidate not found!";
            }
            String candidateName = candidates.getFull_name();
            String candidateEmail = candidates.getEmail();
            UUID position_id=interviewDetails.getPosition_id();
            Users users=usersRepository.findByRole("Interviewer").get(0);
            Position position=positionRepository.findById(position_id).orElse(null);
            if(position==null){
                return "Position not found!";
            }
            String positionTitle = position.getPosition_title();
            Map<String, Object> emailData = new HashMap<>();
            emailData.put("INTERVIEWER_NAME", users.getName());
            emailData.put("CANDIDATE_NAME", candidateName);
            emailData.put("POSITION_TITLE", positionTitle);
            emailData.put("DATE", interviewDetails.getDate().toString());
            emailData.put("TIME", interviewDetails.getTime().toString());
            Interviews interviews1= interviews;
            if(interviews1!=null){
                boolean candidateMailSent = sendEmailWithRetryMechanism(
                        candidateEmail,
                        "Interview Cancelled for " + positionTitle,
                        "CandidateCancelled",
                        emailData
                );


                boolean interviewerMailSent = sendEmailWithRetryMechanism(
                        users.getEmail(),
                        "Interview Cancelled: " + candidateName + " for " + positionTitle,
                        "InterviewerCancelled",
                        emailData
                );

                if (!candidateMailSent || !interviewerMailSent) {
                    return "Failed to send one or both interview scheduling emails!";
                }
                CandidateApplicationsEntity candidateApplications =candidateApplicationsRepository.findByCandidateIdAndPositionId(candidate_id, position_id).get(0);
                LocalDateTime currentTime = LocalDateTime.now();
                candidateApplications.setApplicationStatus("Cancelled");
                interviews1.setStatus("Cancelled");
                candidateApplications.setUpdatedDate(currentTime);
                interviewerRepository.save(interviews1);
                candidateApplicationsRepository.save(candidateApplications);
                return "Interview Cancelled!";
            }
        }
        return "Invalid Status!";
    }


    public String applyInterview(UUID candidateId, UUID positionId) {
        try {
            CandidateApplicationsEntity candidateApplications = new CandidateApplicationsEntity();
            candidateApplications.setApplicationId(UUID.randomUUID());
            candidateApplications.setCandidateId(candidateId);
            candidateApplications.setPositionId(positionId);
            candidateApplications.setApplicationStatus("Shortlisted");
            LocalDateTime time = LocalDateTime.now();

            candidateApplications.setApplicationDate(time);
            candidateApplications.setUpdatedDate(time);
            candidateApplicationsRepository.save(candidateApplications);
            return "Applied for Job!";
        }catch (Exception e){
            return " Couldn't Apply!";
        }
    }


    public List<ResponseDTO> getAllDetailsByCandidateId(UUID candidate_id){
        List<CandidateApplicationsEntity> candidateApplicationsList=candidateApplicationsRepository.findByCandidateIdNative(candidate_id);
        List<ResponseDTO> positionDTOList=new ArrayList<>();
        for (CandidateApplicationsEntity candidateApplications:candidateApplicationsList){
            ApiResponse<ResponseDTO> responseDTOS=feignPositionDTO.getById(candidateApplications.getPositionId());
            ResponseDTO responseDTO=responseDTOS.getData();
            positionDTOList.add(responseDTO);
        }
        return positionDTOList;
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

            // Save updated candidate
            Candidates updatedCandidate = candidateRepository.save(existingCandidate);

            // Convert to DTO and return
            return new CandidatesDTO(updatedCandidate);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't create candidate due to: " + e.getMessage());
        }
    }
}
