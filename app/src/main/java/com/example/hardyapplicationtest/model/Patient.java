package com.example.hardyapplicationtest.model;

public class Patient {
    //private variable
    int Patient_ID;
    public String PatientName;
    public String ContactNo;
    public String EmailID;
    public String DateOfBirth;
    public String Gender;
    public String Weight;
    public byte[] _image;
    public String DiseaseIds;
    public String DiseaseNames;

    //Easy constructor

    public Patient() {
    }

    //constructor
    public Patient(int patient_ID, String patientName, String contactNo, String emailID, String dateOfBirth, String gender, String weight, byte[] _image, String diseaseIds, String diseaseNames) {
        Patient_ID = patient_ID;
        PatientName = patientName;
        ContactNo = contactNo;
        EmailID = emailID;
        DateOfBirth = dateOfBirth;
        Gender = gender;
        Weight = weight;
        this._image = _image;
        DiseaseIds = diseaseIds;
        DiseaseNames = diseaseNames;
    }

    public int getPatient_ID() {
        return Patient_ID;
    }

    public void setPatient_ID(int patient_ID) {
        Patient_ID = patient_ID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public byte[] get_image() {
        return _image;
    }

    public void set_image(byte[] _image) {
        this._image = _image;
    }

    public String getDiseaseIds() {
        return DiseaseIds;
    }

    public void setDiseaseIds(String diseaseIds) {
        DiseaseIds = diseaseIds;
    }

    public String getDiseaseNames() {
        return DiseaseNames;
    }

    public void setDiseaseNames(String diseaseNames) {
        DiseaseNames = diseaseNames;
    }
}
