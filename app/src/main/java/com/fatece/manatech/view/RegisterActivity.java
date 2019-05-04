package com.fatece.manatech.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.fatece.manatech.R;
import com.fatece.manatech.domain.employee.Employee;
import com.fatece.manatech.domain.employee.EmployeeDAO;
import com.fatece.manatech.domain.time.Time;
import com.fatece.manatech.domain.time.TimeDAO;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editFistName, editLastName, editEmail, editUsername, editPassword, editConfirmPw;
    Integer id_time;
    RadioGroup radioGroupFunc;
    RadioButton radioManager, radioDeveloper, radioHR, radioIntern;
    Spinner spinnerTeam;
    Button btnRegister, btnBack;
    Integer function;
    List<Time> times;
    TimeDAO daoTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Spinner
        daoTime = new TimeDAO(this);
        times = daoTime.findAll();
        spinnerTeam = findViewById(R.id.spinnerTeam);
        spinnerTeam.setOnItemSelectedListener(this);
        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(this,
                R.layout.spinner_layout, R.id.txtTeam,times);
        spinnerTeam.setAdapter(adapter);

        editFistName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editEmail = findViewById(R.id.editEmail);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPw = findViewById(R.id.editConfirmPass);

    }
    //set function
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioManager:
                if (checked)
                    function = 1;
                break;
            case R.id.radioDev:
                if (checked)
                    function = 2;
                break;
            case R.id.radioHr:
                if (checked)
                    function = 3;
                break;
            case R.id.radioIntern:
                if (checked)
                    function = 4;
                break;
        }
    }

    //Spinner methods
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
    //Register method
    public void register(View view) {
        Employee employee = new Employee(editFistName.getText().toString(),
                editLastName.getText().toString(), editEmail.getText().toString(),
                editUsername.getText().toString(), editPassword.getText().toString(),
                function, id_time);
        EmployeeDAO dao = new EmployeeDAO(this);
        long id = dao.addEmployee(employee);
        Toast.makeText(this, "Employee " + employee.getFirstName() +
                        "save successfully.", Toast.LENGTH_SHORT).show();
        clearFields();
    }
    //Back to MainActivity
    public void back(View view) {
        finish();
    }

    //ClearAllFields
    public void clearFields() {
        editFistName.setText("");
        editLastName.setText("");
        editEmail.setText("");
        editUsername.setText("");
        editPassword.setText("");
        editConfirmPw.setText("");
        editFistName.clearFocus();
        editLastName.clearFocus();
        editEmail.clearFocus();
        editUsername.clearFocus();
        editPassword.clearFocus();
        editConfirmPw.clearFocus();
    }

}
