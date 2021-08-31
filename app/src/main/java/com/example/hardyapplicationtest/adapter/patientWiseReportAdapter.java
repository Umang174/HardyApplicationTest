package com.example.hardyapplicationtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hardyapplicationtest.R;
import com.example.hardyapplicationtest.model.ReportByDisease;
import com.example.hardyapplicationtest.model.ReportWisePatientList;

import java.util.List;

public class patientWiseReportAdapter extends RecyclerView.Adapter<patientWiseReportAdapter.ViewHolder> {
    List<ReportWisePatientList> reportWisePatientLists;
    Context context;

    public patientWiseReportAdapter(List<ReportWisePatientList> reportWisePatientLists, Context context) {
        this.reportWisePatientLists = reportWisePatientLists;
        this.context = context;
    }

    @NonNull
    @Override
    public patientWiseReportAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_disease_report_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull patientWiseReportAdapter.ViewHolder holder, int position) {
        holder.patientName.setText(reportWisePatientLists.get(position).getP_Name());
        holder.patientID.setText(reportWisePatientLists.get(position).getP_Id());
    }

    @Override
    public int getItemCount() {
        return reportWisePatientLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView patientID;
        public TextView patientName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientID = (TextView)itemView.findViewById(R.id.txt_title_disease);
            patientName = (TextView)itemView.findViewById(R.id.txt_count_disease);
        }
    }
}
