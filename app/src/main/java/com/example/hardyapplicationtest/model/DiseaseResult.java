package com.example.hardyapplicationtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiseaseResult {
    @SerializedName("diseaseData")
    @Expose
    List<Disease> diseaseList;

    public DiseaseResult(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }
}
