package com.fatece.manatech.domain.meeting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fatece.manatech.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MeetingDAO {
    private DBHelper con;
    private SQLiteDatabase db;
    private String tableName = "meeting";

    public MeetingDAO(Context context) {
        con = new DBHelper(context);
        db = con.getWritableDatabase();
    }

    public long add(Meeting meet) {
        ContentValues values = new ContentValues();
        values.put("date", meet.getDateTime());
        values.put("ata", meet.getAta());
        values.put("id_time", meet.getId_time());
        return db.insert(tableName, null, values);
    }

    public long update(Meeting meet) {
        ContentValues values = new ContentValues();
        values.put("date", meet.getDateTime());
        values.put("ata", meet.getAta());
        values.put("id_time", meet.getId_time());
        return db.update(tableName, values,
                "id = " + meet.getId().toString(),null);
    }

    public Meeting find(Integer id) {
        String[] columuns = {"id", "date", "ata", "id_time"};
        Cursor cursor = db.query(tableName, columuns, "id = ?",
                new String[] {id.toString()}, null, null, null);
        if (cursor.moveToFirst()) {
            Meeting meet = new Meeting(cursor.getString(1),
                    cursor.getString(2),cursor.getInt(3));
            return meet;
        }
        return null;
    }

    public List<Meeting> findAll() {
        List<Meeting> meetings = new ArrayList<>();
        String[] columuns = {"id", "date", "ata", "id_time"};

        Cursor cursor = db.query(tableName, columuns, null, null, null,
                null, null);
        if (cursor.moveToFirst()) {
            do {
                Meeting meet = new Meeting(cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                meet.setId(cursor.getInt(0));
                meetings.add(meet);
            } while (cursor.moveToNext());
            return meetings;
        }
        return null;
    }

    public List<Meeting> findByTeam(Integer id) {
        List<Meeting> meetings = new ArrayList<>();
        String[] columns = {"id", "date", "ata", "id_time"};
        Cursor cursor = db.query(tableName,columns,"id_time = ?",
                new String[] {id.toString()},null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Meeting meet = new Meeting(cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                meet.setId(cursor.getInt(0));
                meetings.add(meet);
            } while (cursor.moveToNext());
            return meetings;
        }
        return null;
    }

}
