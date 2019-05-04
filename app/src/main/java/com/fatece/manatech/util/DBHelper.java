package com.fatece.manatech.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fatece.manatech.domain.acitivity.Activity;
import com.fatece.manatech.domain.meeting.Meeting;
import com.fatece.manatech.domain.time.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "manatech.db";

    private static final String EMPLOYEE_TABLE_NAME = "employee";
    private static final String TIME_TABLE_NAME = "time";
    private static final String MEETING_TABLE_NAME = "meeting";
    private static final String ACTIVITY_TABLE_NAME = "activity";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_TIME = "CREATE TABLE IF NOT EXISTS " + TIME_TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY, name_time VARCHAR(30) NOT NULL);";

        String SQL_CREATE_MEETING = "CREATE TABLE IF NOT EXISTS " + MEETING_TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY, date DATETIME NOT NULL, ata TEXT NOT NULL, " +
                "id_time INTEGER, FOREIGN KEY (id_time) REFERENCES  " + TIME_TABLE_NAME +"(id));";

        String SQL_CREATE_ACTIVITY = "CREATE TABLE IF NOT EXISTS " + ACTIVITY_TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY, deadline DATETIME NOT NULL, cost REAL NOT NULL, " +
                "des TEXT NOT NULL, id_time INTEGER, " +
                "done INTEGER DEFAULT 0 NOT NULL, " +
                "FOREIGN KEY (id_time) REFERENCES " + TIME_TABLE_NAME + "(id));";

        String SQL_CREATE_EMPLOYEE = "CREATE TABLE IF NOT EXISTS " + EMPLOYEE_TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY, f_name VARCHAR(30) NOT NULL, l_name VARCHAR(30) NOT NULL, " +
                "email VARCHAR(30) NOT NULL, username VARCHAR(20) NOT NULL, password VARCHAR(20) NOT NULL, " +
                "id_time INTEGER, function INTEGER NOT NULL, " +
                "FOREIGN KEY (id_time) REFERENCES " + TIME_TABLE_NAME + "(id));";

        db.execSQL(SQL_CREATE_MEETING);
        db.execSQL(SQL_CREATE_ACTIVITY);
        db.execSQL(SQL_CREATE_EMPLOYEE);
        db.execSQL(SQL_CREATE_TIME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
