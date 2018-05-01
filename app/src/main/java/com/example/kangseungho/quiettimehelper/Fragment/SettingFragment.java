package com.example.kangseungho.quiettimehelper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kangseungho.quiettimehelper.Alarm.AlarmAdapter;
import com.example.kangseungho.quiettimehelper.AlarmHATT;
import com.example.kangseungho.quiettimehelper.R;

public class SettingFragment extends Fragment {

    private ListView alarmList;
    private AlarmAdapter alarmAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("설정");

        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        alarmList = (ListView) view.findViewById(R.id.alarmListView);

        alarmAdapter = new AlarmAdapter();
        alarmList.setAdapter(alarmAdapter);

        new AlarmHATT(getContext()).Alarm();

        return view;
    }
}
