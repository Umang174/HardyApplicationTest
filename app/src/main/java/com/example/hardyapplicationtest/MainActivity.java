package com.example.hardyapplicationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.example.hardyapplicationtest.R;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = (GridLayout) findViewById(R.id.maingrid);
        setSingleEvent(gridLayout);
    }

    private void setSingleEvent(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0) {
                        Intent addpaitent_intent = new Intent(MainActivity.this, addpaitentActivity.class);
                        startActivity(addpaitent_intent);
                        finish();
                    } else if (finalI == 1) {
                        Intent paitentlist_intent = new Intent(MainActivity.this, paitentlistActivity.class);
                        startActivity(paitentlist_intent);
                        finish();
                    } else if (finalI == 2) {
                        Intent paitentreport_intent = new Intent(MainActivity.this, paitentreportActivity.class);
                        startActivity(paitentreport_intent);
                        finish();
                    }
                }
            });
        }
    }
}