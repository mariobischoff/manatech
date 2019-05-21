package com.fatece.manatech.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fatece.manatech.R;
import com.fatece.manatech.domain.acitivity.Activity;
import com.fatece.manatech.domain.acitivity.ActivityDAO;
import com.fatece.manatech.domain.time.Time;
import com.fatece.manatech.domain.time.TimeDAO;

import java.util.Calendar;
import java.util.List;

public class SetActiActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerTeamSetMeet;
    Integer id_time;
    TimeDAO daoTime;
    List<Time> times;
    TextView txtDate, txtTime, txtDesc, txtVal;
    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_acti);

        //Spinner
        daoTime = new TimeDAO(this);
        times = daoTime.findAll();
        spinnerTeamSetMeet = findViewById(R.id.spinnerTeamSetActi);
        spinnerTeamSetMeet.setOnItemSelectedListener(this);
        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(this,
                R.layout.spinner_layout, R.id.txtTeam,times);
        spinnerTeamSetMeet.setAdapter(adapter);

        //TextView
        txtDate = findViewById(R.id.txtDateSetActi);
        txtTime = findViewById(R.id.txtTimeSetActi);
        txtDesc = findViewById(R.id.txtDescSetActi);
        txtVal = findViewById(R.id.txtValueSetActi);

        id_time = 0;
    }

    public void back(View view) {
        finish();
    }

    public void register(View view) {
        String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        Float value = Float.parseFloat(txtVal.getText().toString());

        String dateTime = date + time;
        String desc = txtDesc.getText().toString();
        if (dateTime.length() <= 0 && desc.length() <= 0 && value.isNaN() ) {
            Toast.makeText(this, "Please, fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Activity acti = new Activity(dateTime, value, desc, id_time, false);
        ActivityDAO actiDAO = new ActivityDAO(this);
        long id = actiDAO.add(acti);
        if (id != -1) {
            txtDate.setText("--:--:----");
            txtTime.setText("00:00");
            txtDesc.setText("");
            Toast.makeText(this, "activity added", Toast.LENGTH_SHORT).show();
            Intent returnToMain = new Intent(this, MainActivity.class);
            setResult(RESULT_OK, returnToMain);
            finish();
        }
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
            String hour = String.valueOf(hourOfDay);
            String min = String.valueOf(minute);
            if (hour.length() < 2) {
                hour = "0" + hour;
            }
            if (min.length() < 2) {
                min = "0" + min;
            }
            String time = hour + ":" + min;
            txtTime.setText(time);
        }
    };

    private DatePickerDialog.OnDateSetListener mDataSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String day = String.valueOf(dayOfMonth);
            String mon = String.valueOf(month + 1);
            if (day.length() < 2) {
                day = "0" + day;
            }
            if (mon.length() < 2) {
                mon = "0" + mon;
            }
            String data = day + "/" + mon + "/" + String.valueOf(year);
            txtDate.setText(data);
        }
    };

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
}
