package com.fatece.manatech.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.fatece.manatech.R;
import com.fatece.manatech.domain.time.Time;
import com.fatece.manatech.domain.time.TimeDAO;

import java.time.MonthDay;
import java.util.Calendar;
import java.util.List;

public class SetMeetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spinnerTeamSetMeet;
    Integer id_time;
    TimeDAO daoTime;
    List<Time> times;
    TextView txtDate, txtTime;
    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_meet);

        //Spinner
        daoTime = new TimeDAO(this);
        times = daoTime.findAll();
        spinnerTeamSetMeet = findViewById(R.id.spinnerTeamSetMeet);
        spinnerTeamSetMeet.setOnItemSelectedListener(this);
        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(this,
                R.layout.spinner_layout, R.id.txtTeam,times);
        spinnerTeamSetMeet.setAdapter(adapter);

        //TxtViews
        txtDate = findViewById(R.id.txtDateSetMeet);
        txtTime = findViewById(R.id.txtTimeSetMeet);

    }

    public void back(View view) {
        finish();
    }

    public void register(View view) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Time time = (Time) parent.getItemAtPosition(position);
        id_time = time.getId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Time time = (Time) parent.getItemAtPosition(0);
        id_time = time.getId();
    }

    @Override
    public void onClick(View v) {

    }

    public void dateClick(View view) {
        showDialog(DATE_DIALOG_ID);
    }

    public void timeClick(View view) {
        showDialog(TIME_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDataSetListener, year, month, day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,mTimeSetListener, 0,0,true);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
            txtTime.setText(time);
        }
    };

    private DatePickerDialog.OnDateSetListener mDataSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String data = String.valueOf(dayOfMonth) + " / " + String.valueOf(month+1) +
                    "/" + String.valueOf(year);
            txtDate.setText(data);
        }
    };
}
