package com.example.hardyapplicationtest.model;

public class ReportByDisease {
    String diseaseName;
    String no_ofCase;

    public ReportByDisease(){}

    public ReportByDisease(String diseaseName, String no_ofCase) {
        this.diseaseName = diseaseName;
        this.no_ofCase = no_ofCase;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getNo_ofCase() {
        return no_ofCase;
    }

    public void setNo_ofCase(String no_ofCase) {
        this.no_ofCase = no_ofCase;
    }
}
