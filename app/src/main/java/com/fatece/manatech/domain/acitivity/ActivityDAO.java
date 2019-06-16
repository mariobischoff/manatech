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
                    act.setId(cursor.getInt(0));
                    activities.add(act);
                } while (cursor.moveToNext());
                return activities;
            }
            return null;
    }

    public List<Activity> findByTeam(Integer id) {
        List<Activity> activities = new ArrayList<>();
        String[] columns = {"id", "deadline", "cost", "des", "id_time", "done"};
        Cursor cursor = db.query(tableName, columns, "id_time = ?",
                new String[]{id.toString()}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                boolean done = cursor.getInt(5) > 0;
                Activity act = new Activity(cursor.getString(1),
                        cursor.getFloat(2), cursor.getString(3),
                        cursor.getInt(4), done);
                act.setId(cursor.getInt(0));
                activities.add(act);
            } while (cursor.moveToNext());
            return activities;
        }
        return null;
    }

    public long update(Activity acti) {
        ContentValues values = new ContentValues();
        values.put("deadline", acti.getDeadline());
        values.put("cost", acti.getCost());
        values.put("des", acti.getDes());
        values.put("id_time", acti.getTime());
        values.put("done", acti.isDone());
        return db.update(tableName, values,
                "id = " + acti.getId().toString(),null);
    }
}
