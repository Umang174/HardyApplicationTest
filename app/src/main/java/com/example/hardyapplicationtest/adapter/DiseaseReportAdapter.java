package com.example.hardyapplicationtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hardyapplicationtest.R;
import com.example.hardyapplicationtest.model.Patient;
import com.example.hardyapplicationtest.model.ReportByDisease;

import java.util.List;

public class DiseaseReportAdapter extends RecyclerView.Adapter<DiseaseReportAdapter.ViewHolder>{
    List<ReportByDisease> reportByDiseaseList;
    Context context;
    private DiseaseReportAdapter.RecyclerViewClickListener listener;

    public DiseaseReportAdapter(List<ReportByDisease> reportByDiseaseList, Context context,DiseaseReportAdapter.RecyclerViewClickListener listener) {
        this.reportByDiseaseList = reportByDiseaseList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public DiseaseReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_disease_report_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseReportAdapter.ViewHolder holder, int position) {

        holder.DiseaseTitle.setText(reportByDiseaseList.get(position).getDiseaseName());
        holder.DiseaseCount.setText(reportByDiseaseList.get(position).getNo_ofCase());

    }

    @Override
    public int getItemCount() {
        return reportByDiseaseList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public LinearLayout row_layout;
        public TextView DiseaseTitle;
        public TextView DiseaseCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            row_layout = (LinearLayout)itemView.findViewById(R.id.row_layout);
            DiseaseTitle = (TextView)itemView.findViewById(R.id.txt_title_disease);
            DiseaseCount = (TextView)itemView.findViewById(R.id.txt_count_disease);
            row_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }

}
