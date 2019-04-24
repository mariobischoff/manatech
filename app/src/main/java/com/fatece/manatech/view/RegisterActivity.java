package com.fatece.manatech.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fatece.manatech.R;
import com.fatece.manatech.model.Employee;

public class RegisterActivity extends AppCompatActivity {

    EditText editFistName, editLastName, editEmail, editUsername, editPassword, editConfirmPw;
    RadioGroup radioGroupFunc;
    RadioButton radioManager, radioDeveloper, radioHR, radioIntern;
    Button btnRegister, btnBack;
    String function;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editFistName = (EditText) findViewById(R.id.editFirstName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editConfirmPw = (EditText) findViewById(R.id.editConfirmPass);


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioManager:
                if (checked)
                    function = "manager";
                break;
            case R.id.radioDev:
                if (checked)
                    function = "dev";
                break;
            case R.id.radioHr:
                if (checked)
                    function = "hr";
                break;
            case R.id.radioIntern:
                if (checked)
                    function = "intern";
                break;
        }
    }

    public void register(View view) {
        Employee employee = new Employee(editFistName.getText().toString(),
                editLastName.getText().toString(), editEmail.getText().toString(),
                editUsername.getText().toString(), editPassword.getText().toString(),function, )
    }
}
