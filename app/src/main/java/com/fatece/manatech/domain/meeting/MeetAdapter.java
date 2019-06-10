package com.fatece.manatech.domain.meeting;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fatece.manatech.R;

import java.util.List;

public class MeetAdapter extends BaseAdapter {
    private final List<Meeting> meetings;
    private final Activity act;

    public MeetAdapter(List<Meeting> meetings, Activity act) {
        this.meetings = meetings;
        this.act = act;
    }
    @Override
    public int getCount() {
        return meetings.size();
    }

    @Override
    public Object getItem(int position) {
        return meetings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return meetings.get(position).getId();
    }

    public void clear() {
        meetings.clear();
    }

    public void addAll(List<Meeting> meets) {
        meetings.addAll(meets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.meet_item, parent, false);

        Meeting meet = meetings.get(position);

        TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
        TextView txtTime = (TextView) view.findViewById(R.id.txtTime);
        ImageView Img = (ImageView) view.findViewById(R.id.imgPage);

        String dateTime = meet.getDateTime();

        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(10);

        txtDate.setText(date);
        txtTime.setText(time);

        return view;
    }
}
