package com.example.hardyapplicationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hardyapplicationtest.adapter.DiseaseReportAdapter;
import com.example.hardyapplicationtest.adapter.patientWiseReportAdapter;
import com.example.hardyapplicationtest.api.client.RestClient;
import com.example.hardyapplicationtest.model.Disease;
import com.example.hardyapplicationtest.model.DiseaseResult;
import com.example.hardyapplicationtest.model.Members;
import com.example.hardyapplicationtest.model.MembersResult;
import com.example.hardyapplicationtest.model.ReportByDisease;
import com.example.hardyapplicationtest.model.ReportWisePatientList;
import com.example.hardyapplicationtest.service.NetworkStateReceiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class paitentreportActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {
    private NetworkStateReceiver networkStateReceiver;
    private RecyclerView disease_recyclerView;
    DiseaseReportAdapter diseaseReportAdapter;
    private DiseaseReportAdapter.RecyclerViewClickListener listener;
    public ArrayList<Members> membersArrayList = new ArrayList<>();
    public ArrayList<Disease> diseaseArrayList = new ArrayList<>();
    static final List<ReportByDisease> reportByDiseaseList = new ArrayList<>();
    List<ReportWisePatientList> reportWisePatientListList = new ArrayList<>();
    Members members;
    ReportWisePatientList reportWisePatientList;
    patientWiseReportAdapter patientWiseReportAdapter;
    public static Dialog dialog;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitentreport);
        progressBar = (ProgressBar) findViewById(R.id.load_progressbar);
        disease_recyclerView = (RecyclerView)findViewById(R.id.disease_report_recylerview);
        disease_recyclerView.setHasFixedSize(true);
        disease_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetAllDisease();



        //filterMemberList();

        listener = new DiseaseReportAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                try {
                    if(v.getId()==R.id.row_layout){
                        int temp_id = filterListofPatient(reportByDiseaseList.get(position).getDiseaseName());
                        filterPatientList(temp_id);
                        //Toast.makeText(paitentreportActivity.this,""+temp_id,Toast.LENGTH_LONG).show();
                    }
                }catch (NullPointerException e){

                }
            }
        };

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void GetAllMembers() {
        showProgressDialog();
        reportByDiseaseList.clear();
        Call<MembersResult> call = RestClient.getRestService().members();
        call.enqueue(new Callback<MembersResult>() {
            @Override
            public void onResponse(Call<MembersResult> call, Response<MembersResult> response) {
                if(response.isSuccessful())
                {
                    if (response.code() == 200) {
                        MembersResult membersResult = response.body();
                        membersArrayList.addAll(membersResult.getMembersList());
                        filterMemberList(membersArrayList);

                        //members = response.body().getMembersList();
                        //setMemberList(membersArrayList);
                    }
                }

            }

            @Override
            public void onFailure(Call<MembersResult> call, Throwable t) {
                hideProgressDialog();
            }
        });
        hideProgressDialog();
    }

    public void setMemberList(ArrayList<Members> members){
        this.membersArrayList = members;
    }

    private int filterListofPatient(String name){
        int j =0;
        for(int i = 0;i<diseaseArrayList.size();i++){
            if(diseaseArrayList.get(i).getTitle() == name){
              j=diseaseArrayList.get(i).getId();
              break;
            }
        }
        return j;
    }

    private void filterPatientList(int selectedID){
        reportWisePatientListList.clear();
        for(int j = 0; j<membersArrayList.size();j++)
        {
            String tempvalues = membersArrayList.get(j).getDisease_ids();
            if( !tempvalues.isEmpty() ) {
                String[] membersDesieasid = membersArrayList.get(j).getDisease_ids().split(",");
                for (String temp : membersDesieasid) {
                    if (selectedID == Integer.valueOf(temp)) {
                        ReportWisePatientList newreportWisePatientList = new ReportWisePatientList();
                        newreportWisePatientList.setP_Id(""+membersArrayList.get(j).getId());
                        newreportWisePatientList.setP_Name(membersArrayList.get(j).getName());
                        reportWisePatientListList.add(newreportWisePatientList);
                    }
                }
            }
        }
        showPopup(paitentreportActivity.this);
    }

    public void showPopup(Activity activity){
        dialog =new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.activity_popup);

        RecyclerView reportwisePatient_recyclerView = dialog.findViewById(R.id.patientlist_reportwise_recylerview);
        reportwisePatient_recyclerView.setHasFixedSize(true);
        reportwisePatient_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        patientWiseReportAdapter = new patientWiseReportAdapter(reportWisePatientListList,paitentreportActivity.this);
        reportwisePatient_recyclerView.setAdapter(patientWiseReportAdapter);
        dialog.show();
    }

    private void filterMemberList(List<Members> members){
        //List<String> DiseaseCount = new ArrayList<>();

        for(int i = 0 ;i <diseaseArrayList.size();i++){
            int counts = 0;
            for(int j = 0; j<members.size();j++)
            {
                String tempvalues = members.get(j).getDisease_ids();
                if( !tempvalues.isEmpty() ) {
                    String[] membersDesieasid = members.get(j).getDisease_ids().split(",");
                    for (String temp : membersDesieasid) {
                        if (diseaseArrayList.get(i).getId() == Integer.valueOf(temp)) {
                            counts++;
                        }
                    }
                }
            }
            //DiseaseCount.add(String.valueOf(counts));
            ReportByDisease reportByDisease = new ReportByDisease();
            reportByDisease.setDiseaseName(diseaseArrayList.get(i).getTitle());
            reportByDisease.setNo_ofCase(""+counts);

            reportByDiseaseList.add(reportByDisease);

        }
        diseaseReportAdapter = new DiseaseReportAdapter(reportByDiseaseList,paitentreportActivity.this,listener);
        disease_recyclerView.setAdapter(diseaseReportAdapter);
    }

    private void GetAllDisease() {
        showProgressDialog();
        Call<DiseaseResult> call = RestClient.getRestService().disease();
        call.enqueue(new Callback<DiseaseResult>() {
            @Override
            public void onResponse(Call<DiseaseResult> call, Response<DiseaseResult> response) {
                if(response.isSuccessful()){
                    if (response.code() == 200) {
                        DiseaseResult diseaseResult = response.body();
                        diseaseArrayList.addAll(diseaseResult.getDiseaseList());
                        GetAllMembers();
                    }
                }

            }

            @Override
            public void onFailure(Call<DiseaseResult> call, Throwable t) {
                hideProgressDialog();
            }
        });
        hideProgressDialog();
    }

    private void hideProgressDialog() {
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(paitentreportActivity.this, MainActivity.class);
        startActivity(in);
        finish();
    }

    @Override
    public void networkAvailable() {
        Intent i = new Intent(this, paitentreportActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    @Override
    public void networkUnavailable() {
        Intent intent = new Intent(paitentreportActivity.this, offlineActivity.class);
        startActivity(intent);
    }
}