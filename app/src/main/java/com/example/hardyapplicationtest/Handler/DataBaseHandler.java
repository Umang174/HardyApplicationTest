package com.example.hardyapplicationtest.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hardyapplicationtest.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MYDB";

    // Patient table name
    private static final String TABLE_PATIENT = "PatientTable";

    //  Table Columns names
    private static final String Patient_Id = "patient_id";
    private static final String Patient_Name = "patient_name";
    private static final String Patient_Contact = "patient_contact";
    private static final String Patient_Email = "patient_email";
    private static final String Patient_DOB = "patient_dob";
    private static final String Patient_Gender = "patient_gender";
    private static final String Patient_Weight = "patient_weight";
    private static final String Patient_Image = "patient_image";
    private static final String Patient_DiseaseIDS = "patient_diseaseids";
    private static final String Patient_DiseaseName = "patient_diseasename";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT + "("
                + Patient_Id + " INTEGER PRIMARY KEY AUTOINCREMENT," + Patient_Name + " TEXT,"
                + Patient_Contact + " TEXT," + Patient_Email + " TEXT,"
                + Patient_DOB + " TEXT," + Patient_Gender + " TEXT,"
                + Patient_Weight + " TEXT," + Patient_Image + " BLOB,"
                + Patient_DiseaseIDS + " TEXT," + Patient_DiseaseName + " TEXT" + ")";
        db.execSQL(CREATE_PATIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        onCreate(db);
    }

    public void addPatient(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Patient_Name, patient.PatientName);
        values.put(Patient_Contact, patient.ContactNo);
        values.put(Patient_Email, patient.EmailID);
        values.put(Patient_DOB, patient.DateOfBirth);
        values.put(Patient_Gender, patient.Gender);
        values.put(Patient_Weight, patient.Weight);
        values.put(Patient_Image, patient._image);
        values.put(Patient_DiseaseIDS, patient.DiseaseIds);
        values.put(Patient_DiseaseName, patient.DiseaseNames);

        db.insert(TABLE_PATIENT, null, values);
        //db.close();

    }

    public List<Patient> getAllPatient() {
        List<Patient> patientList = new ArrayList<Patient>();
        String selectQuery = "SELECT * FROM PatientTable ORDER BY patient_id";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient();
                patient.setPatient_ID(Integer.parseInt(cursor.getString(0)));
                patient.setPatientName(cursor.getString(1));
                patient.setContactNo(cursor.getString(2));
                patient.setEmailID(cursor.getString(3));
                patient.setDateOfBirth(cursor.getString(4));
                patient.setGender(cursor.getString(5));
                patient.setWeight(cursor.getString(6));
                patient.set_image(cursor.getBlob(7));
                patient.setDiseaseIds(cursor.getString(8));
                patient.setDiseaseNames(cursor.getString(9));
                patientList.add(patient);
            } while (cursor.moveToNext());
        }
        db.close();
        return patientList;
    }

}
