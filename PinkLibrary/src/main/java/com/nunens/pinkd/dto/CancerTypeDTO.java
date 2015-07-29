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
public class CancerTypeDTO {

    private int cancerID;
    private String name;
    private long date;
    private String status;
    private int adminID;
    private List<SymptomDTO> symptomList;

    public CancerTypeDTO() {

    }


    public int getCancerID() {
        return cancerID;
    }

    public void setCancerID(int cancerID) {
        this.cancerID = cancerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<SymptomDTO> getSymptomList() {
        return symptomList;
    }

    public void setSymptomList(List<SymptomDTO> symptomList) {
        this.symptomList = symptomList;
    }

}
