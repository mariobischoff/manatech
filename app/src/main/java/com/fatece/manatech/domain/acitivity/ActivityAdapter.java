package com.fatece.manatech.domain.acitivity;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fatece.manatech.R;

import org.w3c.dom.Text;

import java.util.List;

public class ActivityAdapter extends BaseAdapter {
    private final List<com.fatece.manatech.domain.acitivity.Activity> activities;
    private final Activity act;

    public ActivityAdapter(List<com.fatece.manatech.domain.acitivity.Activity> activities, Activity act) {
        this.activities = activities;
        this.act = act;
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Object getItem(int position) {
        return activities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return activities.get(position).getId();
    }

    public void clear() {
        activities.clear();
    }

    public void addAll(List<com.fatece.manatech.domain.acitivity.Activity> acts) {
        activities.addAll(acts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.act_item, parent, false);

        com.fatece.manatech.domain.acitivity.Activity act = activities.get(position);

        TextView txtDate = (TextView) view.findViewById(R.id.txtDateAct);
        TextView txtTime = (TextView) view.findViewById(R.id.txtTimeAct);
        ImageView img = (ImageView) view.findViewById(R.id.imgAct);
        TextView cost = (TextView) view.findViewById(R.id.txtCost);

        if (act.isDone()) {
            img.setImageResource(R.drawable.ic_act_finish);
        } else {
            img.setImageResource(R.drawable.ic_act_open);
        }

        String dateTime = act.getDeadline();

        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(10);

        txtDate.setText(date);
        txtTime.setText(time);
        cost.setText(Double.toString(act.getCost()));

        return view;
    }
}
