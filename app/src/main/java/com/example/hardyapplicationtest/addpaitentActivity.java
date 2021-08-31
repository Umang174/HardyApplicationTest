package com.example.hardyapplicationtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.AlteredCharSequence;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hardyapplicationtest.Handler.DataBaseHandler;
import com.example.hardyapplicationtest.model.Patient;
import com.example.hardyapplicationtest.utils.DatePickerEditText;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class addpaitentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private LinearLayout main_layLayout;
    private Spinner spn_gender;
    private TextView txt_Disease;
    private EditText edit_fullName, edit_contactnumber, edit_email, edit_weight;
    private Button btn_save, btn_capture_image;
    private ImageView img_capture;
    DatePickerEditText DOB_Edittext;

    private static final int CAMERA_REQUEST = 1;
    Bitmap captureImage;
    byte imageInByte[];

    boolean[] selectDisease;
    ArrayAdapter adapter;
    String gender;
    String[] gender_list = {"Male", "Female"};
    String diseaseIds = "";
    String diseaseNames = "";
    // initialise the list items for the alert dialog
    final String[] disease_listItems = new String[]{"Asthma", "Convulsions", "Eye problem", "Juvenile cataracts", "Haemophilia", "Mentally retarded", "Goiter"};
    final boolean[] checkedItems = new boolean[disease_listItems.length];

    final List<String> selectedItems = Arrays.asList(disease_listItems);

    private DataBaseHandler dataBaseHandler;
    private Patient patient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpaitent);
        main_layLayout = (LinearLayout) findViewById(R.id.main_layout);
        spn_gender = (Spinner) findViewById(R.id.edit_gender);

        txt_Disease = (TextView) findViewById(R.id.edit_DiseaseList);
        edit_fullName = (EditText) findViewById(R.id.edit_fullName);
        edit_contactnumber = (EditText) findViewById(R.id.edit_contactnumber);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_weight = (EditText) findViewById(R.id.edit_weight);
        btn_save = (Button) findViewById(R.id.btnSave);
        btn_capture_image = (Button) findViewById(R.id.btnAttachImage);
        img_capture = (ImageView) findViewById(R.id.capture_Image);
        DOB_Edittext = (DatePickerEditText) findViewById(R.id.edit_dob);

        //For Gender Data
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_gender.setAdapter(adapter);
        spn_gender.setOnItemSelectedListener(this);

        //Declare Init Object
        dataBaseHandler = new DataBaseHandler(addpaitentActivity.this);
        patient = new Patient();

        //For Disease Data
        txt_Disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_Disease.setText(null);
                diseaseIds = "";
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        addpaitentActivity.this
                );
                builder.setTitle("Select Disease");

                builder.setMultiChoiceItems(disease_listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                        checkedItems[i] = isChecked;
                        String CurrentItem = selectedItems.get(i);

                    }
                });
                builder.setCancelable(false);

                //For Positive Button
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int k = 0;
                        int t = 0;
                        int count = disease_listItems.length;
                        diseaseNames = "";

                        for (int i = 0; i < checkedItems.length; i++) {

                            if (checkedItems[i]) {
                                int tempid = i + 1;


                                if (t == 0) {
                                    diseaseIds += tempid + "";
                                    diseaseNames += (txt_Disease.getText() + selectedItems.get(i));

                                }
                                if (t > 0) {
                                    diseaseIds += "," + tempid;
                                    diseaseNames += ("," + txt_Disease.getText() + selectedItems.get(i));

                                }
                                t++;
                            }
                        }
                        txt_Disease.setText(diseaseNames);
                    }
                });
                //For Negative Button
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("CLEAR ALL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {
                            checkedItems[i] = false;
                        }
                    }
                });

                builder.show();
            }
        });


      /*  //Request For Open Camera
        if (ContextCompat.checkSelfPermission(addpaitentActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(addpaitentActivity.this, new String[]{Manifest.permission.CAMERA}, 100);
        }*/


        /**
         * open dialog for choose camera
         */

        final String[] option = new String[] {"Take from Camera"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, option);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Select Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (which == 0) {
                    callCamera();
                }

            }
        });
        final AlertDialog capture_dialog = builder.create();


        btn_capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);*/
                capture_dialog.show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //postDataPatient();
                AlertDialog.Builder dialog = new AlertDialog.Builder(
                  addpaitentActivity.this );
                dialog.setTitle("Are you Sure?");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        postDataPatient();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    /**
     * open camera method
     */
    public void callCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 200);
    }

    private void postDataPatient() {
        patient.setPatientName(edit_fullName.getText().toString().trim());
        patient.setContactNo(edit_contactnumber.getText().toString().trim());
        patient.setEmailID(edit_email.getText().toString().trim());
        patient.setDateOfBirth(DOB_Edittext.getText().toString().trim());
        patient.setGender(gender);
        patient.setWeight(edit_weight.getText().toString().trim());

        patient.set_image(imageInByte);

        patient.setDiseaseIds(diseaseIds);
        patient.setDiseaseNames(diseaseNames);


        dataBaseHandler.addPatient(patient);

        Snackbar.make(main_layLayout, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (requestCode == 100) {
            captureImage = (Bitmap) data.getExtras().get("data");
            img_capture.setImageBitmap(captureImage);
        }*/
        if(resultCode!=RESULT_OK)
            return;
        switch (requestCode)
        {
            case CAMERA_REQUEST:
                Bundle extras = data.getExtras();
                if(extras!=null){
                    captureImage = extras.getParcelable("data");
                    //Convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    captureImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imageInByte = stream.toByteArray();
                }
                else if(extras==null) {
                    imageInByte = null;
                }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(addpaitentActivity.this, MainActivity.class);
        startActivity(in);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gender = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}