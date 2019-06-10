package com.fatece.manatech.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fatece.manatech.R;
import com.fatece.manatech.domain.acitivity.Activity;
import com.fatece.manatech.domain.acitivity.ActivityDAO;
import com.fatece.manatech.domain.employee.Employee;
import com.fatece.manatech.domain.employee.EmployeeDAO;
import com.fatece.manatech.domain.meeting.MeetAdapter;
import com.fatece.manatech.domain.meeting.Meeting;
import com.fatece.manatech.domain.meeting.MeetingDAO;
import com.fatece.manatech.domain.time.Time;
import com.fatece.manatech.domain.time.TimeDAO;

import java.io.Serializable;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final int LOGIN_REQUEST = 1, SETUP_MEET_REQUEST = 2, SETUP_ACTI_REQUEST = 3, EDIT_MEET_REQUEST = 4;
    FloatingActionButton fabMenu, fabAddEmployee, fabAddMeet, fabAddAct;
    ListView listMeet, listAct;
    Float translationY = 100f;
    Boolean isMenuOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    TextView txtFirstName, txtLastName, txtFunction, txtTeam;
//    ArrayAdapter<Meeting> adapterMeet;
    MeetAdapter adapterMeet;
    ArrayAdapter<Activity> adapterAct;
    List<Meeting> meetings;
    List<Activity> activities;
    Employee user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFabMenu();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, LOGIN_REQUEST);


        listMeet = findViewById(R.id.listMeet);
        listAct = findViewById(R.id.listAct);

//
//        meetings = new MeetingDAO(this).findAll();
//        activities = new ActivityDAO(this).findAll();
//
//        adapterMeet = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, meetings);
//
//        adapterAct = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, activities);
//
//        listMeet.setAdapter(adapterMeet);
//        listAct.setAdapter(adapterAct);
//
//        listMeet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent iMeet = new Intent(getApplicationContext(), SetMeetActivity.class);
//                Meeting meet = meetings.get(position);
//
//                iMeet.putExtra("edit", true);
//                iMeet.putExtra("meet", meet);
//                startActivityForResult(iMeet, EDIT_MEET_REQUEST);
//                return true;
//            }
//        });
//
//        listMeet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "ata: " + meetings.get(position).getAta(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        listAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "description: " + activities.get(position).getDes(), Toast.LENGTH_SHORT).show();
//            }
//        });
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
                Intent iReg = new Intent(this, RegisterActivity.class);
                startActivity(iReg);
                closeMenu();
                break;
            case R.id.fabAddMeet:
                Intent iMeet = new Intent(this, SetMeetActivity.class);
                iMeet.putExtra("flag", "create");
                startActivityForResult(iMeet, SETUP_MEET_REQUEST);
                closeMenu();
                break;
            case R.id.fabAddAct:
                Intent iActi = new Intent(this, SetActiActivity.class);
                startActivityForResult(iActi, SETUP_ACTI_REQUEST);
                closeMenu();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == LOGIN_REQUEST) {
            if (resultCode == RESULT_OK) {

                EmployeeDAO empDao = new EmployeeDAO(this);
                user = empDao.findEmployee(data.getStringExtra("username"));

                txtFirstName = findViewById(R.id.txtFirtName);
                txtLastName = findViewById(R.id.txtLastName);
                txtFunction = findViewById(R.id.txtFunction);
                txtTeam = findViewById(R.id.txtTeam);

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

                // lists
                meetings = new MeetingDAO(this).findByTeam(user.getId_time());
                activities = new ActivityDAO(this).findAll();

                adapterMeet = new MeetAdapter(meetings, this);

//                adapterMeet = new ArrayAdapter<>(this,
//                        android.R.layout.simple_list_item_1, meetings);

                adapterAct = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, activities);

                listMeet.setAdapter(adapterMeet);
                listAct.setAdapter(adapterAct);

                listMeet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent iMeet = new Intent(getApplicationContext(), SetMeetActivity.class);
                        Meeting meet = meetings.get(position);

                        iMeet.putExtra("edit", true);
                        iMeet.putExtra("meet", meet);
                        startActivityForResult(iMeet, EDIT_MEET_REQUEST);
                        return true;
                    }
                });

                listMeet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "ata: " + meetings.get(position).getAta(), Toast.LENGTH_SHORT).show();
                    }
                });

                listAct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "description: " + activities.get(position).getDes(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        if (requestCode == EDIT_MEET_REQUEST || requestCode == SETUP_MEET_REQUEST) {
            adapterMeet.clear();
            meetings = new MeetingDAO(this).findByTeam(user.getId_time());
            adapterMeet.addAll(meetings);
            adapterMeet.notifyDataSetChanged();
        }

        if (requestCode == SETUP_ACTI_REQUEST) {
            activities = new ActivityDAO(this).findAll();
            adapterAct.add(activities.get(activities.size() - 1));
            adapterAct.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

//        adapterMeet.clear();
//        meetings = new MeetingDAO(getApplicationContext()).findAll();
//        adapterMeet.addAll(meetings);
//        adapterMeet.notifyDataSetChanged();
//
//        adapterAct.clear();
//        activities = new ActivityDAO(this).findAll();
//        adapterAct.addAll(activities);
//        adapterAct.notifyDataSetChanged();
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
