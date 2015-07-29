/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunens.pinkd.dto;

/**
 * @author Sipho
 */
public class RequestDTO {

    public static final int register = 1;
    public static final int login = 2;
    public static final int getUser = 3;
    public static final int addCalender = 4;
    public static final int updateCalender = 5;
    public static final int getCalender = 6;
    public static final int addCampaign = 7;
    public static final int updateCampaign = 8;
    public static final int getCampaign = 9;
    public static final int addCancer = 10;
    public static final int updateCancer = 11;
    public static final int getCancer = 12;
    public static final int addEvent = 13;
    public static final int updateEvent = 14;
    public static final int getEvent = 15;
    public static final int addExam = 16;
    public static final int updateExam = 17;
    public static final int getExam = 18;
    public static final int addExperience = 19;
    public static final int updateExperience = 20;
    public static final int getExperience = 21;
    public static final int addGameQuestion = 22;
    public static final int updateGameQuestion = 23;
    public static final int getGameQuestion = 24;
    public static final int addMyth = 25;
    public static final int updateMyth = 26;
    public static final int getMyth = 27;
    public static final int addQuestionAndAnswer = 28;
    public static final int updateQuestionAndAnswer = 29;
    public static final int getQuestionAndAnswer = 30;
    public static final int addSymptom = 31;
    public static final int updateSymptom = 32;
    public static final int getSymptom = 33;
    public static final int addTutorial = 34;
    public static final int updateTutorial = 35;
    public static final int getTutorial = 36;
    public static final int adminLogin = 37;
    public static final int getPendingQuestions = 38;
    public static final int getRandomMyth = 39;

    private Integer requestType, radiusKM, adminID, calenderID, campaignID, cancerID, eventID, examID, experienceID, gameID, mythID, questionAnswerID, symptomID, tutorialID, userID;
    private AdminDTO adminDTO;
    private CalenderDTO calenderDTO;
    private CampaignDTO campaignDTO;
    private CancerTypeDTO cancerDTO;
    private EventDTO eventDTO;
    private ExamDTO examDTO;
    private ExperienceDTO experienceDTO;
    private GameDTO gameDTO;
    private MythDTO mythDTO;
    private QuestionAnswerDTO questionAnswerDTO;
    private SymptomDTO symptomDTO;
    private TutorialDTO tutorialDTO;
    private UserDTO userDTO;

    public static int getGetUser() {
        return getUser;
    }

    public static int getRegister() {
        return register;
    }

    public static int getLogin() {
        return login;
    }

    public static int getAddCalender() {
        return addCalender;
    }

    public static int getUpdateCalender() {
        return updateCalender;
    }

    public static int getGetCalender() {
        return getCalender;
    }

    public static int getAddCampaign() {
        return addCampaign;
    }

    public static int getUpdateCampaign() {
        return updateCampaign;
    }

    public static int getGetCampaign() {
        return getCampaign;
    }

    public static int getAddCancer() {
        return addCancer;
    }

    public static int getUpdateCancer() {
        return updateCancer;
    }

    public static int getGetCancer() {
        return getCancer;
    }

    public static int getAddEvent() {
        return addEvent;
    }

    public static int getUpdateEvent() {
        return updateEvent;
    }

    public static int getGetEvent() {
        return getEvent;
    }

    public static int getAddExam() {
        return addExam;
    }

    public static int getUpdateExam() {
        return updateExam;
    }

    public static int getGetExam() {
        return getExam;
    }

    public static int getAddExperience() {
        return addExperience;
    }

    public static int getUpdateExperience() {
        return updateExperience;
    }

    public static int getGetExperience() {
        return getExperience;
    }

    public static int getAddGameQuestion() {
        return addGameQuestion;
    }

    public static int getUpdateGameQuestion() {
        return updateGameQuestion;
    }

    public static int getGetGameQuestion() {
        return getGameQuestion;
    }

    public static int getAddMyth() {
        return addMyth;
    }

    public static int getUpdateMyth() {
        return updateMyth;
    }

    public static int getGetMyth() {
        return getMyth;
    }

    public static int getAddQuestionAndAnswer() {
        return addQuestionAndAnswer;
    }

    public static int getUpdateQuestionAndAnswer() {
        return updateQuestionAndAnswer;
    }

    public static int getGetQuestionAndAnswer() {
        return getQuestionAndAnswer;
    }

    public static int getAddSymptom() {
        return addSymptom;
    }

    public static int getUpdateSymptom() {
        return updateSymptom;
    }

    public static int getGetSymptom() {
        return getSymptom;
    }

    public static int getAddTutorial() {
        return addTutorial;
    }

    public static int getUpdateTutorial() {
        return updateTutorial;
    }

    public static int getGetTutorial() {
        return getTutorial;
    }

    public static int getAdminLogin() {
        return adminLogin;
    }

    public static int getGetPendingQuestions() {
        return getPendingQuestions;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public Integer getRadiusKM() {
        return radiusKM;
    }

    public void setRadiusKM(Integer radiusKM) {
        this.radiusKM = radiusKM;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public Integer getCalenderID() {
        return calenderID;
    }

    public void setCalenderID(Integer calenderID) {
        this.calenderID = calenderID;
    }

    public Integer getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(Integer campaignID) {
        this.campaignID = campaignID;
    }

    public Integer getCancerID() {
        return cancerID;
    }

    public void setCancerID(Integer cancerID) {
        this.cancerID = cancerID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public Integer getExamID() {
        return examID;
    }

    public void setExamID(Integer examID) {
        this.examID = examID;
    }

    public Integer getExperienceID() {
        return experienceID;
    }

    public void setExperienceID(Integer experienceID) {
        this.experienceID = experienceID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public Integer getMythID() {
        return mythID;
    }

    public void setMythID(Integer mythID) {
        this.mythID = mythID;
    }

    public Integer getQuestionAnswerID() {
        return questionAnswerID;
    }

    public void setQuestionAnswerID(Integer questionAnswerID) {
        this.questionAnswerID = questionAnswerID;
    }

    public Integer getSymptomID() {
        return symptomID;
    }

    public void setSymptomID(Integer symptomID) {
        this.symptomID = symptomID;
    }

    public Integer getTutorialID() {
        return tutorialID;
    }

    public void setTutorialID(Integer tutorialID) {
        this.tutorialID = tutorialID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public AdminDTO getAdminDTO() {
        return adminDTO;
    }

    public void setAdminDTO(AdminDTO adminDTO) {
        this.adminDTO = adminDTO;
    }

    public CalenderDTO getCalenderDTO() {
        return calenderDTO;
    }

    public void setCalenderDTO(CalenderDTO calenderDTO) {
        this.calenderDTO = calenderDTO;
    }

    public CampaignDTO getCampaignDTO() {
        return campaignDTO;
    }

    public void setCampaignDTO(CampaignDTO campaignDTO) {
        this.campaignDTO = campaignDTO;
    }

    public CancerTypeDTO getCancerDTO() {
        return cancerDTO;
    }

    public void setCancerDTO(CancerTypeDTO cancerDTO) {
        this.cancerDTO = cancerDTO;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public ExamDTO getExamDTO() {
        return examDTO;
    }

    public void setExamDTO(ExamDTO examDTO) {
        this.examDTO = examDTO;
    }

    public ExperienceDTO getExperienceDTO() {
        return experienceDTO;
    }

    public void setExperienceDTO(ExperienceDTO experienceDTO) {
        this.experienceDTO = experienceDTO;
    }

    public GameDTO getGameDTO() {
        return gameDTO;
    }

    public void setGameDTO(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    public MythDTO getMythDTO() {
        return mythDTO;
    }

    public void setMythDTO(MythDTO mythDTO) {
        this.mythDTO = mythDTO;
    }

    public QuestionAnswerDTO getQuestionAnswerDTO() {
        return questionAnswerDTO;
    }

    public void setQuestionAnswerDTO(QuestionAnswerDTO questionAnswerDTO) {
        this.questionAnswerDTO = questionAnswerDTO;
    }

    public SymptomDTO getSymptomDTO() {
        return symptomDTO;
    }

    public void setSymptomDTO(SymptomDTO symptomDTO) {
        this.symptomDTO = symptomDTO;
    }

    public TutorialDTO getTutorialDTO() {
        return tutorialDTO;
    }

    public void setTutorialDTO(TutorialDTO tutorialDTO) {
        this.tutorialDTO = tutorialDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
