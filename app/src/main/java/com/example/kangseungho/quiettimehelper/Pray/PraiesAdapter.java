package com.example.kangseungho.quiettimehelper.Pray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kangseungho.quiettimehelper.R;
import com.example.kangseungho.quiettimehelper.WordItem;

import java.util.LinkedList;

public class PraiesAdapter extends BaseAdapter {
    LinkedList<String> praiesItem;
    ViewHolder viewHolder;

    @Override
    public int getCount() {
        return praiesItem.size();
    }

    @Override
    public String getItem(int i) {
        return praiesItem.get(i);
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
            convertView = inflater.inflate(R.layout.praies_list_item, parent, false);

            viewHolder = new ViewHolder();
            //viewHolder.prayTitle = (TextView) convertView.findViewById(R.id.wordNumber_tv);
            //viewHolder.pray = (TextView) convertView.findViewById(R.id.word_tv);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String wordItem = getItem(position);
        int number = Integer.parseInt(WordItem.instance.getPassageStartNum()) + position;

        //viewHolder.prayTitle.setText(number + " ");
        //viewHolder.pray.setText(wordItem);

        return convertView;
    }

    public void addItem(LinkedList<String> wordsItem) {
        this.praiesItem = wordsItem;
    }

    class ViewHolder {
        TextView prayTitle;
        TextView pray;
    }
}
