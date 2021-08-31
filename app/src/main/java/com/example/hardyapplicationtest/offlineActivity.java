package com.example.hardyapplicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class offlineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(offlineActivity.this, MainActivity.class);
        startActivity(in);
        finish();
        super.onBackPressed();
    }
}