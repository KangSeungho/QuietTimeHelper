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

public class PrayAdapter extends BaseAdapter {
    LinkedList<String> prayTitle;
    LinkedList<String> pray;
    ViewHolder viewHolder;

    @Override
    public int getCount() {
        return prayTitle.size();
    }

    @Override
    public String getItem(int i) {
        return prayTitle.get(i);
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
            viewHolder.prayTitle = (TextView) convertView.findViewById(R.id.pray_title_tv);
            viewHolder.pray = (TextView) convertView.findViewById(R.id.pray_tv);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String strPrayTitle = prayTitle.get(position);
        String strPray = pray.get(position);

        viewHolder.prayTitle.setText(strPrayTitle);
        viewHolder.pray.setText(strPray);

        return convertView;
    }

    public void addItem(LinkedList<String> prayTitle, LinkedList<String> pray) {
        this.prayTitle = prayTitle;
        this.pray = pray;
    }

    class ViewHolder {
        TextView prayTitle;
        TextView pray;
    }
}
