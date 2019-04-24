package com.fatece.manatech.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fatece.manatech.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class TimeDAO {
    private DBHelper con;
    private SQLiteDatabase db;
    private String tableName = "time";

    public TimeDAO(Context context) {
        con = new DBHelper(context);
        db = con.getWritableDatabase();
    }


    public long add(Time time) {
        ContentValues values = new ContentValues();
        values.put("name_time", time.getNameTime());
        return db.insert(tableName, null, values);
    }

    public List<Time> findAll() {
        List<Time> times = new ArrayList<>();
        String[] columuns = {"id", "name_time"};

        Cursor cursor = db.query(tableName, columuns, null, null, null,
                null, null);
        if (cursor.moveToFirst()) {
            do {
                Time time = new Time(cursor.getString(1));
                time.setId(cursor.getInt(0));

                times.add(time);
            } while (cursor.moveToNext());
            return times;
        }
        return null;
    }


}
