package com.example.kangseungho.quiettimehelper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kangseungho.quiettimehelper.R;
import com.example.kangseungho.quiettimehelper.WordItem;

public class WordsFragment extends Fragment{

    private TextView textViewHtmlDocument;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("말씀 읽기");

        View view = inflater.inflate(R.layout.fragment_words, container, false);

        textViewHtmlDocument = (TextView) view.findViewById(R.id.textView);
        textViewHtmlDocument.setMovementMethod(new ScrollingMovementMethod());

        textViewHtmlDocument.setText(WordItem.instance.test);

        return view;
    }
}
