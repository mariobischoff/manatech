package com.fatece.manatech.domain.acitivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fatece.manatech.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {
    private DBHelper con;
    private SQLiteDatabase db;
    private String tableName = "activity";

    public ActivityDAO(Context context) {
        this.con = new DBHelper(context);
        this.db = con.getWritableDatabase();
    }

    public long add(Activity act) {
        ContentValues values = new ContentValues();
        values.put("deadline", act.getDeadline());
        values.put("cost", act.getCost());
        values.put("des", act.getDes());
        values.put("id_time", act.getTime());
        values.put("done", act.isDone());
        return db.insert(tableName, null, values);
    }

    public List<Activity> findAll() {
        List<Activity> activities = new ArrayList<>();
        String[] columns = {"id", "deadline", "cost", "des", "id_time", "done"};
        Cursor cursor = db.query(tableName, columns, null, null,
                null, null, null);
        if (cursor.moveToFirst()) {
            do {
                boolean done = cursor.getInt(5) > 0;
                Activity act = new Activity(cursor.getString(1),
                        cursor.getFloat(2), cursor.getString(3),
                        cursor.getInt(4), done);
                activities.add(act);
            } while (cursor.moveToNext());
            return activities;
        }
        return null;
    }
}
