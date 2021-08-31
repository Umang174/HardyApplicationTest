package com.example.hardyapplicationtest.model;

public class ReportWisePatientList {
    String P_Id;
    String P_Name;

    public ReportWisePatientList(){

    }

    public ReportWisePatientList(String p_Id, String p_Name) {
        P_Id = p_Id;
        P_Name = p_Name;
    }

    public String getP_Id() {
        return P_Id;
    }

    public void setP_Id(String p_Id) {
        P_Id = p_Id;
    }

    public String getP_Name() {
        return P_Name;
    }

    public void setP_Name(String p_Name) {
        P_Name = p_Name;
    }
}
