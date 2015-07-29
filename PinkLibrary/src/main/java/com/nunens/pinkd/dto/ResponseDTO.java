/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunens.pinkd.dto;

import java.util.List;

/**
 *
 * @author Sipho
 */
public class ResponseDTO {

    private int statusCode, status;
    private String message, sessionID;
    private String requestStatus;
    public static final int OK = 0;
    public static final int SERVER_ERROR = 100;
    public static final int DATABASE_ERROR = 101;
    public static final int DATA_NOT_FOUND = 102;
    public static final int UNKNOWN_REQUEST = 103;
    private AdminDTO admin;
    private CalenderDTO calender;
    private CampaignDTO campaign;
    private CancerTypeDTO cancer;
    private EventDTO event;
    private ExamDTO exam;
    private ExperienceDTO experience;
    private GameDTO game;
    private MythDTO myth;
    private QuestionAnswerDTO questionAnswer;
    private SymptomDTO symptom;
    private TutorialDTO tutorial;
    private UserDTO user;
    private List<AdminDTO> admins;
    private List<CalenderDTO> calenders;
    private List<CampaignDTO> campaigns;
    private List<CancerTypeDTO> cancers;
    private List<EventDTO> events;
    private List<ExamDTO> exams;
    private List<ExperienceDTO> experiences;
    private List<GameDTO> games;
    private List<MythDTO> myths;
    private List<QuestionAnswerDTO> questionAnswers;
    private List<SymptomDTO> symptoms;
    private List<TutorialDTO> tutorials;
    private List<UserDTO> users;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public AdminDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminDTO admin) {
        this.admin = admin;
    }

    public CalenderDTO getCalender() {
        return calender;
    }

    public void setCalender(CalenderDTO calender) {
        this.calender = calender;
    }

    public CampaignDTO getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignDTO campaign) {
        this.campaign = campaign;
    }

    public CancerTypeDTO getCancer() {
        return cancer;
    }

    public void setCancer(CancerTypeDTO cancer) {
        this.cancer = cancer;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public ExamDTO getExam() {
        return exam;
    }

    public void setExam(ExamDTO exam) {
        this.exam = exam;
    }

    public ExperienceDTO getExperience() {
        return experience;
    }

    public void setExperience(ExperienceDTO experience) {
        this.experience = experience;
    }

    public GameDTO getGame() {
        return game;
    }

    public void setGame(GameDTO game) {
        this.game = game;
    }

    public MythDTO getMyth() {
        return myth;
    }

    public void setMyth(MythDTO myth) {
        this.myth = myth;
    }

    public QuestionAnswerDTO getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(QuestionAnswerDTO questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public SymptomDTO getSymptom() {
        return symptom;
    }

    public void setSymptom(SymptomDTO symptom) {
        this.symptom = symptom;
    }

    public TutorialDTO getTutorial() {
        return tutorial;
    }

    public void setTutorial(TutorialDTO tutorial) {
        this.tutorial = tutorial;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<AdminDTO> getAdmins() {
        return admins;
    }

    public void setAdmins(List<AdminDTO> admins) {
        this.admins = admins;
    }

    public List<CalenderDTO> getCalenders() {
        return calenders;
    }

    public void setCalenders(List<CalenderDTO> calenders) {
        this.calenders = calenders;
    }

    public List<CampaignDTO> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignDTO> campaigns) {
        this.campaigns = campaigns;
    }

    public List<CancerTypeDTO> getCancers() {
        return cancers;
    }

    public void setCancers(List<CancerTypeDTO> cancers) {
        this.cancers = cancers;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }

    public List<ExamDTO> getExams() {
        return exams;
    }

    public void setExams(List<ExamDTO> exams) {
        this.exams = exams;
    }

    public List<ExperienceDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceDTO> experiences) {
        this.experiences = experiences;
    }

    public List<GameDTO> getGames() {
        return games;
    }

    public void setGames(List<GameDTO> games) {
        this.games = games;
    }

    public List<MythDTO> getMyths() {
        return myths;
    }

    public void setMyths(List<MythDTO> myths) {
        this.myths = myths;
    }

    public List<QuestionAnswerDTO> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswerDTO> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public List<SymptomDTO> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<SymptomDTO> symptoms) {
        this.symptoms = symptoms;
    }

    public List<TutorialDTO> getTutorials() {
        return tutorials;
    }

    public void setTutorials(List<TutorialDTO> tutorials) {
        this.tutorials = tutorials;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

}
