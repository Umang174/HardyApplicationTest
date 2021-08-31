package com.example.hardyapplicationtest.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.widget.AppCompatEditText;

import com.example.hardyapplicationtest.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerEditText extends AppCompatEditText {
    private final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    Calendar calendar = Calendar.getInstance();

//   public Calendar getCalendar() {
//      return calendar;
//   }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        setText(mSimpleDateFormat.format(calendar.getTime()));
    }

    public DatePickerEditText(Context context) {
        super(context);
    }

    public DatePickerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DatePickerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setInputType(InputType.TYPE_NULL);
        setFocusable(false);
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                if(b){
                    new DatePickerDialog(getContext(),
                            R.style.my_dialog_theme,
                            listener,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                    ).show();

                }
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),
                        R.style.my_dialog_theme,
                        listener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });


        setText(mSimpleDateFormat.format(calendar.getTime()));
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(year, month, day);
            setText(mSimpleDateFormat.format(calendar.getTime()));
        }
    };
}
