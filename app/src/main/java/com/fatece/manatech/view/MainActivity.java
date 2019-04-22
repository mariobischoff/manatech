package com.fatece.manatech.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.fatece.manatech.R;
import com.fatece.manatech.model.Activity;
import com.fatece.manatech.model.Employee;
import com.fatece.manatech.model.EmployeeDAO;
import com.fatece.manatech.model.Meeting;
import com.fatece.manatech.model.Time;
import com.fatece.manatech.model.db.DBHelper;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
