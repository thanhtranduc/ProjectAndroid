package com.example.BasicUI;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: ThanhTran
 * Date: 10/9/13
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileActivity extends Activity
{
    private DatePicker dpResult;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private TextView buttonBirthday;
    private TextView buttonTimeOnline;
    private TextView radioSex;
    private RadioGroup radioGroup;
    RadioButton radioButton;
    private TimePicker timePicker1;
    private TextView txtTimeOnline;
    private TextView txtBirthday;
    private TextView txtSex;

    static final int DATE_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID = 888;
    static final int SEX_DIALOG_ID = 777;
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.profile);
        TextView txtName = (TextView)findViewById(R.id.txtName);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        txtName.setText(" "+name);
        setCurrentDateOnView();
        setCurrentTimeOnView();
        addListenerOnButtonBirthday();
        addListenerOnButtonTime();
        addListenerRadioSex();
    }



    private void addListenerRadioSex() {
        radioSex = (TextView)findViewById(R.id.Gender);
        radioGroup = (RadioGroup)findViewById(R.id.radioSex);
        radioSex.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.setVisibility(View.VISIBLE);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                txtSex = (TextView) findViewById(R.id.txtGender);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selectedId);
                txtSex.setText(radioButton.getText());
                radioGroup.setVisibility(View.INVISIBLE);
            }
        });
    }

    // display current date
    public void setCurrentDateOnView() {

        txtBirthday = (TextView) findViewById(R.id.txtBirthday);
        dpResult = (DatePicker) findViewById(R.id.dpResult);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        txtBirthday.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        dpResult.init(year, month, day, null);

    }

    public void setCurrentTimeOnView() {

        txtTimeOnline = (TextView) findViewById(R.id.txtOnline);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // set current time into textview
        txtTimeOnline.setText(
                new StringBuilder().append(pad(hour))
                        .append(":").append(pad(minute)));

        // set current time into timepicker
        timePicker1.setCurrentHour(hour);
        timePicker1.setCurrentMinute(minute);

    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void addListenerOnButtonBirthday() {
        buttonBirthday = (TextView)findViewById(R.id.birthday);

        buttonBirthday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              //  DatePicker datePicker = (DatePicker)findViewById(R.id.dpResult);
//                datePicker.setVisibility(View.INVISIBLE);
                showDialog(DATE_DIALOG_ID);
            //    datePicker.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, year, month,
                        day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        timePickerListener, hour, minute,false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            txtBirthday.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };


    public void addListenerOnButtonTime() {

        buttonTimeOnline = (TextView) findViewById(R.id.online);

        buttonTimeOnline.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    // set current time into textview
                    txtTimeOnline.setText(new StringBuilder().append(pad(hour))
                            .append(":").append(pad(minute)));

                    // set current time into timepicker
                    timePicker1.setCurrentHour(hour);
                    timePicker1.setCurrentMinute(minute);

                }
            };

}
