package com.example.kangseungho.quiettimehelper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kangseungho.quiettimehelper.R;

public class PrayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("기도");

        View view = inflater.inflate(R.layout.fragment_words, container, false);

        return view;
    }
}
