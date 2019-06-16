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
import android.widget.Button;
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

    Spinner spinnerTeamSetActi;
    Integer id_time;
    TimeDAO daoTime;
    List<Time> times;
    TextView txtDate, txtTime, txtDesc, txtVal;
    Button btnRegister, btnRemove;
    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_acti);

        Intent i = getIntent();

        //Spinner
        daoTime = new TimeDAO(this);
        times = daoTime.findAll();
        spinnerTeamSetActi = findViewById(R.id.spinnerTeamSetActi);
        spinnerTeamSetActi.setOnItemSelectedListener(this);
        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(this,
                R.layout.spinner_layout, R.id.txtTeam,times);
        spinnerTeamSetActi.setAdapter(adapter);

        //TextView
        txtDate = findViewById(R.id.txtDateSetActi);
        txtTime = findViewById(R.id.txtTimeSetActi);
        txtDesc = findViewById(R.id.txtDescSetActi);
        txtVal = findViewById(R.id.txtValueSetActi);

        btnRegister = findViewById(R.id.btnRegisterSetActi);
        btnRemove = findViewById(R.id.btnRemoveSetActi);

        id_time = 0;

        Boolean edit = i.getBooleanExtra("edit", false);
        Boolean view = i.getBooleanExtra("view", false);

        if (view) {
            final Activity acti = (Activity) i.getSerializableExtra("acti");
            String dateTime = acti.getDeadline();
            String date = dateTime.substring(0, 10);
            String time = dateTime.substring(10);
            txtDate.setText(date);
            txtTime.setText(time);
            txtDesc.setText(acti.getDes());
            txtVal.setText(Double.toString(acti.getCost()));
            spinnerTeamSetActi.setSelection(acti.getTime() - 1);

            btnRegister.setEnabled(false);
            btnRegister.setVisibility(View.GONE);

            btnRemove.setEnabled(false);
            btnRemove.setVisibility(View.GONE);

            txtDate.setFocusable(false);
            txtDate.setKeyListener(null);

            txtTime.setFocusable(false);
            txtTime.setKeyListener(null);

            txtVal.setFocusable(false);

            txtDesc.setFocusable(false);

            spinnerTeamSetActi.setEnabled(false);
        }

        if (edit) {
            btnRegister.setText("Edit");
            final Activity acti = (Activity) i.getSerializableExtra("acti");
            String dateTime = acti.getDeadline();
            String date = dateTime.substring(0, 10);
            String time = dateTime.substring(10);
            txtDate.setText(date);
            txtTime.setText(time);
            txtDesc.setText(acti.getDes());
            txtVal.setText(Double.toString(acti.getCost()));
            spinnerTeamSetActi.setSelection(acti.getTime() - 1);

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long selectedItemId = spinnerTeamSetActi.getSelectedItemId() + 1;
                    String date = txtDate.getText().toString();
                    String time = txtTime.getText().toString();
                    String dateTime = date + time;
                    String desc = txtDesc.getText().toString();
                    float cost = Float.parseFloat(txtVal.getText().toString());
                    if (dateTime.length() <= 0 || desc.length() <= 0) {
                        Toast.makeText(getApplicationContext(),
                                "Please, fill all the fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Activity new_acti = new Activity(dateTime, cost, desc,(int) selectedItemId, false);
                    new_acti.setId(acti.getId());

                    ActivityDAO dao = new ActivityDAO(getApplicationContext());
                    long id = dao.update(new_acti);
                    if (id != -1) {
                        txtDate.setText("--:--:----");
                        txtTime.setText("00:00");
                        txtDesc.setText("");
                        txtVal.setText("");
                        Intent returnToMain = new Intent(getApplicationContext(), MainActivity.class);
                        setResult(RESULT_OK, returnToMain);
                        finish();
                    }
                }
            });
            btnRemove.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ActivityDAO dao = new ActivityDAO(getApplicationContext());
                    long id = dao.remove(acti);
                    if (id != -1) {
                        txtDate.setText("--:--:----");
                        txtTime.setText("00:00");
                        txtDesc.setText("");
                        txtVal.setText("");
                        Intent returnToMain = new Intent(getApplicationContext(), MainActivity.class);
                        setResult(RESULT_OK, returnToMain);
                        finish();
                    }
                    return true;
                }
            });
        } else {
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long selectedItemId = spinnerTeamSetActi.getSelectedItemId() + 1;
                    String date = txtDate.getText().toString();
                    String time = txtTime.getText().toString();
                    String dateTime = date + time;
                    String desc = txtDesc.getText().toString();
                    float cost = Float.parseFloat(txtVal.getText().toString());
                    if (dateTime.length() <= 0 || desc.length() <= 0) {
                        Toast.makeText(getApplicationContext(),
                                "Please, fill all the fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Activity new_acti = new Activity(dateTime, cost, desc,(int) selectedItemId, false);

                    ActivityDAO dao = new ActivityDAO(getApplicationContext());
                    long id = dao.add(new_acti);
                    if (id != -1) {
                        txtDate.setText("--:--:----");
                        txtTime.setText("00:00");
                        txtDesc.setText("");
                        txtVal.setText("");
                        Intent returnToMain = new Intent(getApplicationContext(), MainActivity.class);
                        setResult(RESULT_OK, returnToMain);
                        finish();
                    }
                }
            });

        }
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
