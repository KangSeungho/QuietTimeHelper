package com.example.kangseungho.quiettimehelper.Words;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kangseungho.quiettimehelper.R;
import com.example.kangseungho.quiettimehelper.WordItem;

import java.util.LinkedList;

public class WordsAdapter extends BaseAdapter {
    LinkedList<String> wordsItem;
    ViewHolder viewHolder;

    @Override
    public int getCount() {
        return wordsItem.size();
    }

    @Override
    public String getItem(int i) {
        return wordsItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();


        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.words_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.wordNumber = (TextView) convertView.findViewById(R.id.wordNumber_tv);
            viewHolder.word = (TextView) convertView.findViewById(R.id.word_tv);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String wordItem = getItem(position);
        int number = Integer.parseInt(WordItem.instance.getPassageStartNum()) + position;

        viewHolder.wordNumber.setText(number + " ");
        viewHolder.word.setText(wordItem);

        return convertView;
    }

    public void addItem(LinkedList<String> wordsItem) {
        this.wordsItem = wordsItem;
    }

    class ViewHolder {
        TextView wordNumber;
        TextView word;
    }
}
