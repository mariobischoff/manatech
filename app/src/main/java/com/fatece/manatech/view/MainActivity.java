package com.fatece.manatech.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fatece.manatech.R;
import com.fatece.manatech.domain.acitivity.Activity;
import com.fatece.manatech.domain.acitivity.ActivityDAO;
import com.fatece.manatech.domain.employee.Employee;
import com.fatece.manatech.domain.employee.EmployeeDAO;
import com.fatece.manatech.domain.meeting.Meeting;
import com.fatece.manatech.domain.meeting.MeetingDAO;
import com.fatece.manatech.domain.time.Time;
import com.fatece.manatech.domain.time.TimeDAO;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int LOGIN_REQUEST = 1;
    FloatingActionButton fabMenu, fabAddEmployee, fabAddMeet, fabAddAct;
    ListView listMeet, listAct;
    Float translationY = 100f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    TextView txtFirstName, txtLastName, txtFunction, txtTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFabMenu();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, LOGIN_REQUEST);


        listMeet = findViewById(R.id.listMeet);
        listAct = findViewById(R.id.listAct);

        List<Meeting> meetings = new MeetingDAO(this).findAll();
        List<Activity> activities = new ActivityDAO(this).findAll();

        ArrayAdapter<Meeting> adapterMeet = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, meetings);

        ArrayAdapter<Activity> adapterAct = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, activities);

        listMeet.setAdapter(adapterMeet);
        listAct.setAdapter(adapterAct);

    }

    private void initFabMenu() {
        fabMenu = findViewById(R.id.fabMenu);
        fabAddEmployee = findViewById(R.id.fabAddEmployee);
        fabAddMeet = findViewById(R.id.fabAddMeet);
        fabAddAct = findViewById(R.id.fabAddAct);
        fabAddEmployee.setAlpha(0f);
        fabAddMeet.setAlpha(0f);
        fabAddAct.setAlpha(0f);
        fabAddEmployee.setTranslationY(translationY);
        fabAddMeet.setTranslationY(translationY);
        fabAddAct.setTranslationY(translationY);
        fabMenu.setOnClickListener(this);
        fabAddEmployee.setOnClickListener(this);
        fabAddMeet.setOnClickListener(this);
        fabAddAct.setOnClickListener(this);
    }

    private void openMenu() {
        isMenuOpen = !isMenuOpen;
        fabMenu.animate().setInterpolator(interpolator).rotation(90f)
                .setDuration(300).start();
        fabAddEmployee.animate().translationY(0f).alpha(1f)
                .setInterpolator(interpolator)
                .setDuration(300).start();
        fabAddMeet.animate().translationY(0f).alpha(1f)
                .setInterpolator(interpolator)
                .setDuration(300).start();
        fabAddAct.animate().translationY(0f).alpha(1f)
                .setInterpolator(interpolator)
                .setDuration(300).start();
    }

    private void closeMenu() {
        isMenuOpen = !isMenuOpen;
        fabMenu.animate().setInterpolator(interpolator).rotation(0f)
                .setDuration(300).start();
        fabAddEmployee.animate().translationY(0f).alpha(0f)
                .setInterpolator(interpolator)
                .setDuration(300).start();
        fabAddMeet.animate().translationY(0f).alpha(0f)
                .setInterpolator(interpolator)
                .setDuration(300).start();
        fabAddAct.animate().translationY(0f).alpha(0f)
                .setInterpolator(interpolator)
                .setDuration(300).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabMenu:
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fabAddEmployee:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                closeMenu();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == LOGIN_REQUEST) {
            if (resultCode == RESULT_OK) {

                EmployeeDAO dao = new EmployeeDAO(this);
                Employee user = dao.findEmployee(data.getStringExtra("username"));

                txtFirstName = (TextView) findViewById(R.id.txtFirtName);
                txtLastName = (TextView) findViewById(R.id.txtLastName);
                txtFunction = (TextView) findViewById(R.id.txtFunction);
                txtTeam = (TextView) findViewById(R.id.txtTeam);

                txtFirstName.setText(capitalize(user.getFirstName()));
                txtLastName.setText(capitalize(user.getLastName()));

                switch (user.getFunction()) {
                    case 1:
                        txtFunction.setText("Manager");
                        break;
                    case 2:
                        txtFunction.setText("Developer");
                        break;
                    case 3:
                        txtFunction.setText("H.R");
                        break;
                    case 4:
                        txtFunction.setText("Trainee");
                        break;
                }

                TimeDAO daoTeam = new TimeDAO(this);
                Time team = daoTeam.find(user.getId_time());
                txtTeam.setText(capitalize(team.getNameTime()));
            }
        }
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
