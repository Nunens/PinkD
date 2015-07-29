/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunens.pinkd.dto;

/**
 *
 * @author Sipho
 */
public class ExperienceDTO {

    private int experienceID;
    private String story;
    private long date;
    private String status;
    private int userID;
    private String username;

    public ExperienceDTO() {

    }

    public int getExperienceID() {
        return experienceID;
    }

    public void setExperienceID(int experienceID) {
        this.experienceID = experienceID;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
