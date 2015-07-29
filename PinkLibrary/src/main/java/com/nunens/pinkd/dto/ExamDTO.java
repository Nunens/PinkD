/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunens.pinkd.dto;

/**
 * @author Sipho
 */
public class ExamDTO {

    private int examID;
    private String stage;
    private String content;
    private String note;
    private String noteContent;
    private long date;
    private String status;
    private int adminID;
    private int cancerID;
    private String cancerName;

    public ExamDTO() {

    }

    public int getExamID() {
        return examID;
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
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

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public int getCancerID() {
        return cancerID;
    }

    public void setCancerID(int cancerID) {
        this.cancerID = cancerID;
    }

    public String getCancerName() {
        return cancerName;
    }

    public void setCancerName(String cancerName) {
        this.cancerName = cancerName;
    }
}
