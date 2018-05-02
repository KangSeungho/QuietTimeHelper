package com.example.kangseungho.quiettimehelper.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kangseungho.quiettimehelper.Pray.PrayAdapter;
import com.example.kangseungho.quiettimehelper.R;
import com.example.kangseungho.quiettimehelper.WordItem;

public class PrayFragment extends Fragment {

    private TextView today, title, bible;
    private ListView prayList;
    private PrayAdapter prayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("기도");

        View view = inflater.inflate(R.layout.fragment_pray, container, false);

        today = (TextView) view.findViewById(R.id.words_today_tv);
        title = (TextView) view.findViewById(R.id.words_title_tv);
        bible = (TextView) view.findViewById(R.id.words_bible_tv);
        prayList = (ListView) view.findViewById(R.id.pray_list);

        today.setText(WordItem.instance.getDate());
        title.setText(WordItem.instance.getTitle());
        bible.setText(
                "(" + WordItem.instance.getBible() + " " + WordItem.instance.getChapter() + "장 " + WordItem.instance.getPassageStartNum() + "~" + WordItem.instance.getPassageEndNum() + "절)"
        );

        prayAdapter = new PrayAdapter();
        prayAdapter.addItem(WordItem.instance.getPrayTitle(), WordItem.instance.getPray());
        prayList.setAdapter(prayAdapter);

        return view;
    }
}
