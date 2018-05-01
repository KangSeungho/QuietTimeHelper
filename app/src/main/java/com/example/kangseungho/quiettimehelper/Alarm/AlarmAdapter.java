package com.example.kangseungho.quiettimehelper.Alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.kangseungho.quiettimehelper.R;

import java.util.ArrayList;

public class AlarmAdapter extends BaseAdapter{
    ArrayList<AlarmItem> alarmItems = new ArrayList<>();
    ViewHolder viewHolder;

    @Override
    public int getCount() {
        return alarmItems.size();
    }

    @Override
    public AlarmItem getItem(int i) {
        return alarmItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        //alarmItems = AlarmTestData.getAlarmItems();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alarm_list_view, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.dayTv = (TextView) convertView.findViewById(R.id.dayTv);
            viewHolder.ampmBtn = (ToggleButton) convertView.findViewById(R.id.ampmBtn);
            viewHolder.hourEt = (EditText) convertView.findViewById(R.id.hourEt);
            viewHolder.minuteEt = (EditText) convertView.findViewById(R.id.minuteEt);
            viewHolder.alarmSwitch = (Switch) convertView.findViewById(R.id.alarmSwitch);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 데이터 셋팅
        AlarmItem alarmItem = getItem(position);

        if(alarmItem.isOnoff()) {
            viewHolder.ampmBtn.setChecked(alarmItem.isAmpm());
            viewHolder.hourEt.setText(alarmItem.getHour());
            viewHolder.minuteEt.setText(alarmItem.getMinute());

            viewHolder.ampmBtn.setActivated(true);
            viewHolder.hourEt.setActivated(true);
            viewHolder.minuteEt.setActivated(true);
        }
        else {
            viewHolder.ampmBtn.setActivated(false);
            viewHolder.hourEt.setActivated(false);
            viewHolder.minuteEt.setActivated(false);
        }
        viewHolder.dayTv.setText(alarmItem.getDay());
        viewHolder.alarmSwitch.setChecked(alarmItem.isOnoff());

        return convertView;
    }

    class ViewHolder {
        TextView dayTv;
        ToggleButton ampmBtn;
        EditText hourEt;
        EditText minuteEt;
        Switch alarmSwitch;
    }

    enum Day {
        일요일, 월요일, 화요일, 수요일, 목요일, 금요일, 토요일
    }
}