package com.example.kangseungho.quiettimehelper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kangseungho.quiettimehelper.AlarmHATT;
import com.example.kangseungho.quiettimehelper.R;

public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("설정");

        View view = inflater.inflate(R.layout.fragment_words, container, false);

        new AlarmHATT(getContext()).Alarm();

        return view;
    }
}
