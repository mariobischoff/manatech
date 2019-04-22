package com.fatece.manatech.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fatece.manatech.R;
import com.fatece.manatech.model.Employee;
import com.fatece.manatech.model.EmployeeDAO;


public class LoginActivity extends AppCompatActivity {


    EditText editUsername, editPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
    }

    public void clickLogin(View v) {

        EmployeeDAO dao = new EmployeeDAO(this);

        Employee employee = dao.findEmployee(editUsername.getText().toString());

        Toast.makeText(this, "User: "+employee.getFirstName(), Toast.LENGTH_LONG).show();

        if(employee != null) {
            if (editPassword.getText().toString() == employee.getPassword()) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("employee", employee);
                Toast.makeText(this,"username and password macth", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        } else {
            Toast.makeText(this,"Invalid username or password", Toast.LENGTH_LONG).show();
        }
    }


}
