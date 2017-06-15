package com.android.ls.quickindex;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/14.
 */

class MainAdapter extends BaseAdapter {
    private ArrayList<Friend> data;
    private ViewHolder holder;

    public MainAdapter(ArrayList<Friend> data) {
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position).name;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(getItem(position));
        char letter = data.get(position).pinyin.toUpperCase().charAt(0);
        holder.head.setText(String.valueOf(letter));
        if(position>0){
            char preLetter = data.get(position - 1).pinyin.toUpperCase().charAt(0);
            if(letter == preLetter){
                holder.head.setVisibility(View.GONE);
            }else {
                holder.head.setVisibility(View.VISIBLE);
            }
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.head)
        TextView head;
        @BindView(R.id.name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
