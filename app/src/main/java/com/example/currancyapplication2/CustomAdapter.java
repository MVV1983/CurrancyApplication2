package com.example.currancyapplication2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<CurrancyItem> {
    private List<CurrancyItem> listData;
    private LayoutInflater layoutInflater;

    public CustomAdapter(@NonNull Context context, int resource, List<CurrancyItem> listData, LayoutInflater layoutInflater) {
        super(context, resource, listData);
        this.layoutInflater = layoutInflater;
        this.listData = listData;
    }


    @SuppressLint({"InflateParams", "SetTextI18n"})
    public View getView(@NonNull int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CurrancyItem currancyItem = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null, true);
            holder = new ViewHolder();
            holder.codeName = (TextView) convertView.findViewById(R.id.codeName);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.value = (TextView) convertView.findViewById(R.id.Value);
            holder.previousValue = (TextView) convertView.findViewById(R.id.previousValue);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.codeName.setText(currancyItem.getCharCode());
        holder.value.setText(Double.toString(currancyItem.getValue()));
        holder.name.setText(currancyItem.getName());
        holder.previousValue.setText(Double.toString(currancyItem.getPrevious()));

        if(currancyItem.getValue()>currancyItem.getPrevious()){
            holder.value.setTextColor(Color.GREEN);
        }
        else{
            holder.value.setTextColor(Color.RED);
        }

        return convertView;
    }


    private static class ViewHolder {
        TextView codeName;
        TextView name;
        TextView value;
        TextView previousValue;
    }

    public void update(List<CurrancyItem> items) {

        listData.clear();
        listData.addAll(items);
        notifyDataSetChanged();
    }

}