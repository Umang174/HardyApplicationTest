package com.example.hardyapplicationtest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hardyapplicationtest.R;
import com.example.hardyapplicationtest.model.Patient;

import java.io.ByteArrayInputStream;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    List<Patient> patientList;
    Context context;

    public PatientAdapter(List<Patient> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_paitentlist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.ViewHolder holder, int position) {
        holder.PatientName.setText(patientList.get(position).PatientName);
        holder.Contact.setText(patientList.get(position).ContactNo);
        holder.Email.setText(patientList.get(position).EmailID);
        holder.DOB.setText(patientList.get(position).DateOfBirth);
        holder.Gender.setText(patientList.get(position).Gender);
        holder.Weight.setText(patientList.get(position).Weight);
        holder.Disease.setText(patientList.get(position).DiseaseNames);

        //For ImageView
        byte[] outputimage = patientList.get(position)._image;
        if(outputimage == null ){
            holder.PatientImageView.setImageResource(R.drawable.capture_image);
        }
        else{
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outputimage);
            Bitmap finalImage = BitmapFactory.decodeStream(imageStream);
            holder.PatientImageView.setImageBitmap(finalImage);
        }

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView PatientName;
        private TextView Contact;
        private TextView Email;
        private TextView DOB;
        private TextView Gender;
        private TextView Weight;
        private TextView Disease;
        private ImageView PatientImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            PatientName = (TextView) itemView.findViewById(R.id.txtPatientName);
            Contact = (TextView) itemView.findViewById(R.id.txtContact);
            Email = (TextView) itemView.findViewById(R.id.txtemail);
            DOB = (TextView) itemView.findViewById(R.id.txtDOB);
            Gender = (TextView) itemView.findViewById(R.id.txtgender);
            Weight = (TextView) itemView.findViewById(R.id.txtweight);
            Disease = (TextView) itemView.findViewById(R.id.txtdisease);
            PatientImageView = (ImageView) itemView.findViewById(R.id.patient_Image);

        }
    }
}
