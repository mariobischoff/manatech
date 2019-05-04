package com.fatece.manatech.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fatece.manatech.R;
import com.fatece.manatech.domain.meeting.Meeting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MeetAdapter extends ArrayAdapter<Meeting> {
    private final Context context;
    private final ArrayList<Meeting> meetings;

    public MeetAdapter(Context context, ArrayList<Meeting> meetings) {
        super(context, R.layout.meet_item, meetings);
        this.context = context;
        this.meetings = meetings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.meet_item, parent, false);

        TextView date = (TextView) rowView.findViewById(R.id.txtDate);

        return rowView;
    }

    static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
