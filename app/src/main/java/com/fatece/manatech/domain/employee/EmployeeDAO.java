package com.fatece.manatech.domain.employee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fatece.manatech.util.DBHelper;

public class EmployeeDAO {

    private DBHelper con;
    private SQLiteDatabase db;

    public EmployeeDAO(Context context) {
        con = new DBHelper(context);
        db = con.getWritableDatabase();
    }

    public long addEmployee(Employee employee) {
        ContentValues values = new ContentValues();
        values.put("f_name", employee.getFirstName());
        values.put("l_name", employee.getLastName());
        values.put("email", employee.getEmail());
        values.put("username", employee.getUsername());
        values.put("password", employee.getPassword());
        values.put("id_time", employee.getId_time());
        values.put("function", employee.getFunction());
        long id = db.insert("employee", null, values);
        db.close();
        return id;
    }

    public Employee findEmployee(String username) {
        String[] columns = {"id", "f_name", "l_name", "email", "username", "password", "function", "id_time"};
        Cursor cursor = db.query("employee", columns, "username = ?", new String[] {username},
                null, null, null);
        if (cursor.moveToFirst()) {
            Employee employee = new Employee(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3), username ,cursor.getString(5),
                    cursor.getInt(6),cursor.getInt(7));
            return employee;
        }
        return null;
    }
}
