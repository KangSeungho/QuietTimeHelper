package com.example.kangseungho.quiettimehelper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kangseungho.quiettimehelper.R;
import com.example.kangseungho.quiettimehelper.WordItem;
import com.example.kangseungho.quiettimehelper.Words.WordsAdapter;

public class WordsFragment extends Fragment{

    private TextView today, title, bible;
    private ListView wordsList;
    private WordsAdapter wordsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("말씀 읽기");

        View view = inflater.inflate(R.layout.fragment_words, container, false);

        today = (TextView) view.findViewById(R.id.words_today_tv);
        title = (TextView) view.findViewById(R.id.words_title_tv);
        bible = (TextView) view.findViewById(R.id.words_bible_tv);
        wordsList = (ListView) view.findViewById(R.id.words_list);

        title.setText(WordItem.instance.getTitle());
        bible.setText(
                "(" + WordItem.instance.getBible() + " " + WordItem.instance.getChapter() + "장 " + WordItem.instance.getPassageStartNum() + "~" + WordItem.instance.getPassageEndNum() + "절)"
        );

        wordsAdapter = new WordsAdapter();
        wordsAdapter.addItem(WordItem.instance.getWords());
        wordsList.setAdapter(wordsAdapter);

        return view;
    }
}
