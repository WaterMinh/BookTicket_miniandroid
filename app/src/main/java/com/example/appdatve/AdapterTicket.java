package com.example.appdatve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterTicket extends ArrayAdapter<TIcketItemView> {
    private Context mCtx;
    private int mLayout;
    private List<TIcketItemView> mLst;

    public AdapterTicket(Context context, int resource, List<TIcketItemView> objects) {
        super(context, resource, objects);
        this.mCtx = context;
        this.mLayout = resource;
        this.mLst = objects;

    }



    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View item = convertView;
        if (item == null){
            item = LayoutInflater.from(mCtx).inflate(mLayout, null);

        }
        TIcketItemView tk = mLst.get(position);

        ((TextView) item.findViewById(R.id.itemId)).setText(String.valueOf(tk.getId()));
        ((TextView) item.findViewById(R.id.itemNgayDi)).setText(tk.getDate().toString());
        ((TextView) item.findViewById(R.id.itemGaDi)).setText(String.valueOf(tk.getTen_ga_di()));
        ((TextView) item.findViewById(R.id.itemGaDen)).setText(String.valueOf(tk.getTen_ga_den()));

        return item;
    }
}
