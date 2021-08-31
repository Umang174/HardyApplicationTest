package com.example.hardyapplicationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hardyapplicationtest.Handler.DataBaseHandler;
import com.example.hardyapplicationtest.adapter.PatientAdapter;
import com.example.hardyapplicationtest.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class paitentlistActivity extends AppCompatActivity {
    private DataBaseHandler dataBaseHandler;
    private Patient patient;
    private RecyclerView patient_recyclerview;
    private ArrayList<Patient> patientArrayList = new ArrayList<>();
    private PatientAdapter patientAdapter;
    private TextView txtDisease,txtCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitentlist);
        txtDisease = (TextView)findViewById(R.id.title_disease);
        txtCount = (TextView)findViewById(R.id.count_disease);

        patient_recyclerview = (RecyclerView)findViewById(R.id.allpaitentlist_recylerview);
        patient_recyclerview.setHasFixedSize(true);
        patient_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        //Declare Init Object
        dataBaseHandler = new DataBaseHandler(paitentlistActivity.this);
        patient = new Patient();

        List<Patient> patientList = dataBaseHandler.getAllPatient();
        for (Patient pn : patientList){
            pn.getPatientName();
            pn.getContactNo();
            pn.getEmailID();
            pn.getGender();
            pn.getDateOfBirth();
            pn.getWeight();
            pn.get_image();
            pn.getDiseaseNames();
            patientArrayList.add(pn);
        }
        patientAdapter = new PatientAdapter(patientArrayList,this);
        patient_recyclerview.setAdapter(patientAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(paitentlistActivity.this, MainActivity.class);
        startActivity(in);
        finish();
    }
}